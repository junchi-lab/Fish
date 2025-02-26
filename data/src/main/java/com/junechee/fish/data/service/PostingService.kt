package com.junechee.fish.data.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.junechee.fish.data.model.BoardParam
import com.junechee.fish.data.model.BoardParcel
import com.junechee.fish.data.model.ContentParam
import com.junechee.fish.data.retrofit.BoardService
import com.junechee.fish.domain.model.ACTION_POSTED
import com.junechee.fish.domain.usecase.file.UploadImageUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PostingService : LifecycleService() {

    @Inject
    lateinit var uploadImageUseCase: UploadImageUseCase

    @Inject
    lateinit var boardService: BoardService

    companion object {
        const val EXTRA_BOARD = "extra_board"
        const val CHANNEL_ID = "게시글 업로드"
        const val CHANNEL_NAME = "게시글 업로드 채널"
        const val FOREGROUND_NOTIFICATION_ID = 1000
    }

    @Suppress("LABEL_NAME_CLASH")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()

        startForeground()

        intent?.run {
            if (hasExtra(EXTRA_BOARD)) {
                val boardParcel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    getParcelableExtra(EXTRA_BOARD, BoardParcel::class.java)
                } else {
                    getParcelableExtra(EXTRA_BOARD)
                }

                boardParcel?.run {
                    lifecycleScope.launch(Dispatchers.IO) {
                        postBoard(this@run)
                    }
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.description = "백그라운드에서 글을 업로드 합니다."

        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)

    }

    private fun startForeground() {
        val notification = NotificationCompat.Builder(this, CHANNEL_ID).build()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            startForeground(
                FOREGROUND_NOTIFICATION_ID,
                notification,
                ServiceInfo.FOREGROUND_SERVICE_TYPE_SHORT_SERVICE
            )
        } else {
            startForeground(
                FOREGROUND_NOTIFICATION_ID,
                notification
            )
        }
    }

    private suspend fun postBoard(boardParcel: BoardParcel) {
        // 업로드
        val uploadedImages = boardParcel.images.mapNotNull { image ->
            uploadImageUseCase(image).getOrNull()
        }
        val content = ContentParam(
            text = boardParcel.content,
            images = uploadedImages
        )
        val boardParam = BoardParam(
            title = boardParcel.title,
            content = content.toJson()
        )
        val requestBody = boardParam.toRequestBody()
        boardService.postBoard(requestBody)

        sendBroadcast(
            Intent(
                ACTION_POSTED
            ).apply {
                setPackage(packageName)
            }
        )

        // 서비스 종료
        stopForeground(STOP_FOREGROUND_DETACH)
    }
}


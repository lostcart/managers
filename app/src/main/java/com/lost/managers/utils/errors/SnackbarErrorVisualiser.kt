package com.lost.managers.utils.errors

import android.content.Context
import android.view.View
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.lost.data.mapper.DomainErrorMapper
import com.lost.domain.models.DomainError
import com.lost.managers.R
import javax.inject.Inject

class SnackbarErrorVisualiser @Inject constructor(private val domainErrorMapper: DomainErrorMapper) {
    private var isSnackbarShown: Boolean = false

    fun show(view: View, throwable: Throwable) {
        val domainError = domainErrorMapper.mapFrom(throwable)
        createMessage(view.context, domainError).also { message: String ->
            val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            snackbar.addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    super.onDismissed(transientBottomBar, event)
                    isSnackbarShown = false
                }

                override fun onShown(transientBottomBar: Snackbar?) {
                    super.onShown(transientBottomBar)
                    isSnackbarShown = true
                }
            })

            if (!isSnackbarShown) {
                snackbar.show()
                isSnackbarShown = true
            }
        }
    }

    private fun createMessage(context: Context, error: DomainError) = when (error) {
        is DomainError.Offline -> context.getString(R.string.error_offline)
        is DomainError.Timeout -> context.getString(R.string.error_timeout)
        is DomainError.Unauthorized -> context.getString(R.string.error_unauthorised)
        is DomainError.Forbidden -> context.getString(R.string.error_forbidden)
        is DomainError.Generic.Http -> context.getString(
            R.string.error_generic_error_http,
            error.code,
            error.message
        )
        is DomainError.Generic.Unknown -> context.getString(
            R.string.error_generic_error,
            error.message
        )
    }
}
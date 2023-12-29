package com.pixsterstudio.androidarckxml.utils

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pixsterstudio.androidarckxml.R
import com.pixsterstudio.androidarckxml.databinding.PermissionsDialogBinding

class CustomDialog private constructor(
    private val context: Context,
    private val title:String,
    private val desc:String,
    private val isCenterButtonVisible:Boolean,
    private val cancelBtnName:String,
    private val yesBtnName:String,
    private val centerBtnName:String,
    private val dismissListener:((alertDialog: AlertDialog)->Unit)?,
    private val approveListener:((alertDialog:AlertDialog)->Unit)?
) {

    class Builder(val context: Context){
        private var title:String = context.resources.getString(R.string.label_permissions_required)
        private  var desc:String = context.resources.getString(R.string.label_you_need_to_allow_permissions_if_want_to_access_feature)
        private var isCenterButtonVisible:Boolean=false
        private var cancelBtnName:String= context.resources.getString(R.string.btn_no)
        private var yesBtnName:String= context.resources.getString(R.string.btn_yes)
        private  var centerBtnName:String=""
        private var dismissListener:((alertDialog:AlertDialog)->Unit)?=null
        private var approveListener:((alertDialog:AlertDialog)->Unit)?=null


        fun setTitle(title:String):Builder{
            this.title = title
            return this
        }

        fun setDescription(desc:String):Builder{
            this.desc = desc
            return this
        }

        fun isCenterButtonVisible(boolean: Boolean):Builder{
            this.isCenterButtonVisible = boolean
            return this
        }

        fun setCancelButtonName(name:String):Builder{
            this.cancelBtnName = name
            return this
        }

        fun setApproveButtonName(name:String):Builder{
            this.yesBtnName = name
            return this
        }

        fun setCenterButtonName(name:String):Builder{
            this.centerBtnName = name
            return this
        }

        fun setApproveListener(listener:((alertDialog:AlertDialog)->Unit)):Builder{
            this.approveListener = listener
            return this
        }

        fun setDismissListener(listener:((alertDialog:AlertDialog)->Unit)):Builder{
            this.dismissListener = listener
            return this
        }

        fun build():CustomDialog{
            return CustomDialog(
                this.context,
                this.title,
                this.desc,
                this.isCenterButtonVisible,
                this.cancelBtnName,
                this.yesBtnName,
                this.centerBtnName,
                this.dismissListener,
                this.approveListener)
        }

        fun show(){
            build().show()
        }

    }

    fun show(){
         val dialogView = PermissionsDialogBinding.inflate(
            LayoutInflater.from(context)
        )
        val builder = MaterialAlertDialogBuilder(context, R.style.dialog)
        if (isCenterButtonVisible){
            dialogView.textViewCenterText.show()
            dialogView.linearLayoutButtons.hide()
        }else{
            dialogView.textViewCenterText.hide()
            dialogView.linearLayoutButtons.show()
        }
        dialogView.textViewTitle.text = title
        dialogView.textViewDesc.text = desc
        dialogView.textViewNo.text = cancelBtnName
        dialogView.textViewYes.text = yesBtnName
        dialogView.textViewCenterText.text = centerBtnName
        builder.setView(dialogView.root)
        val alert = builder.create()

        dialogView.textViewYes.setOnClickListener {
            approveListener?.invoke(alert)
        }
        dialogView.textViewNo.setOnClickListener {
            dismissListener?.invoke(alert)
        }
        dialogView.textViewCenterText.setOnClickListener{
            approveListener?.invoke(alert)
        }
        alert.setCanceledOnTouchOutside(false)
        alert.setCancelable(false)
        alert.show()
    }

}
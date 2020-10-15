package com.example.propertymanagement.ui.property.fragments


import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.propertymanagement.R
import com.example.propertymanagement.data.models.ImageDetails
import com.example.propertymanagement.data.networks.PropertyManagementAPI
import com.example.propertymanagement.data.repositories.PropertyRepository
import com.example.propertymanagement.helpers.toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_add_property.*
import kotlinx.android.synthetic.main.bottom_sheet.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File

class BottomSheetFragment : BottomSheetDialogFragment(), View.OnClickListener {

    private val CAMERA_REQUEST_CODE = 100
    private val GALLERY_REQUEST_CODE = 101

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_open_camera.setOnClickListener(this)
        button_open_gallery.setOnClickListener(this)
    }

    //SET BUTTON TASK
    override fun onClick(view: View) {
        when (view) {
            button_open_camera -> requestCamera()
            button_open_gallery -> requestGallery()
        }
    }

    //REQUEST & CHECK CAMERA PERMISSION
    private fun requestCamera() {
        Dexter.withContext(activity)
            .withPermission(android.Manifest.permission.CAMERA)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                    activity!!.toast("Permission granted")
                    openCamera()
                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                    activity!!.toast("Permission denied")
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: PermissionRequest?,
                    token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                }

            }).check()
    }


    //REQUEST & CHECK GALLERY PERMISSION
    private fun requestGallery() {
        Dexter.withContext(activity)
            .withPermissions(
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    if (report!!.areAllPermissionsGranted()) {
                        activity!!.toast("Permission Granted")
                        openGallery()
                    } else if (report.isAnyPermissionPermanentlyDenied) {
                        activity!!.toast("Change permission in settings")
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    p1: PermissionToken?
                ) {

                }

            }).onSameThread()
            .check()
    }

    //OPEN CAMERA FOR RESULT
    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }

    //OPEN GALLERY FOR RESULT
    private fun openGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_PICK
        intent.type = "image/*"
        startActivityForResult(intent, GALLERY_REQUEST_CODE)

    }


    //RESULT OF CAMERA/GALLERY
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val bitmap = data.getParcelableExtra<Bitmap>("data")
            val uri = getImageUri(activity!!, bitmap!!)
            val path = getPathFromUri(activity!!, uri)

            activity!!.tv_path_property.text = path.toString()

            Log.d("path", "CAMERA REQUEST" + path.toString())
            uploadImage(path.toString())
        }
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK) {

            val path = getPathFromUri(activity!!, data?.data)

            Log.d("path", "GALLERY REQUEST" + path.toString())
            uploadImage(path.toString())
        }
        dismiss()
    }

    //GET URI FROM BITMAP
    private fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()

        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)

        val path: String = MediaStore.Images.Media.insertImage(
            inContext.contentResolver, inImage
            , "Title", null)


        return Uri.parse(path)
    }

    //GET ACTUAL PATH
    private fun getPathFromUri(inContext: Context, uri: Uri?): String? {
        val cursor: Cursor? = inContext.contentResolver.query(uri!!, null, null, null, null)
        cursor!!.moveToFirst()
        val idx: Int = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
        return cursor.getString(idx)
    }

    //select image u get bitmap
    //bitmap -> uri
    //uri -> path

    //using retrofit and api

    private fun uploadImage(path: String){
        val file = File(path)

        var requestFile = RequestBody.create(MediaType.parse(ImageDetails.KEY_IMAGE_TYPE), file)
        var body = MultipartBody.Part.createFormData(ImageDetails.KEY_IMAGE_NAME, file.name, requestFile)

        PropertyRepository().uploadImage(body)

    }

}
package com.example.lupita.viewModels.models

import android.net.Uri
import android.os.Bundle

class StartActionModel(val action: String, val bundle: Bundle? = null, val uri: Uri? = null) {

    var code: Int = 0

    constructor(action: String, bundle: Bundle?, code: Int) : this(action, bundle) {
        this.code = code
    }
}
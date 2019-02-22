/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.deviceinfo.firmwareversion;

import android.os.Build;
import android.support.annotation.VisibleForTesting;
import android.text.BidiFormatter;

import com.android.settings.R;

import android.os.SystemProperties;

import com.android.settings.deviceinfo.BuildNumberPreferenceController;
import com.android.settings.deviceinfo.DeviceModelPreferenceController;

public class BuildNumberDialogController {

    @VisibleForTesting
    static final int BUILD_NUMBER_VALUE_ID = R.id.build_number_value;

    private final FirmwareVersionDialogFragment mDialog;

    public BuildNumberDialogController(FirmwareVersionDialogFragment dialog) {
        mDialog = dialog;
    }

    private String getSimplixOneBuild(){
        String buildDate = SystemProperties.get("com.simplixone.build_date","");
        String buildType = SystemProperties.get("com.simplixone.build_type","unofficial").toUpperCase();
        return buildDate.equals("") ? "" : "SimplixOne-9.0-Explorer-" + buildDate + "-" + buildType;
    }

    /**
     * Updates the build number to the dialog.
     */
    public void initialize() {
        
        StringBuilder sb = new StringBuilder();
        sb.append(BidiFormatter.getInstance().unicodeWrap(
                TextUtils.isEmpty(Build.VENDOR.BUILD_NUMBER_OVERRIDE) ? Build.DISPLAY : Build.VENDOR.BUILD_NUMBER_OVERRIDE));
        String simplixOneBuild = getSimplixOneBuild();
        if (!simplixOneBuild.equals("")){
            sb.append("\n");
            sb.append(simplixOneBuild);
        }
        sb.append("\n");
        sb.append(DeviceModelPreferenceController.getDeviceModel());
        mDialog.setText(BUILD_NUMBER_VALUE_ID, sb.toString());
    }
}

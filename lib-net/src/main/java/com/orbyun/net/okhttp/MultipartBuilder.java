package com.orbyun.net.okhttp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by 吴春娜 on 2017/12/22.
 */

public class MultipartBuilder {
    public static MultipartBody filesToMultipartBody(List<File> files) {
        MultipartBody.Builder builder = new MultipartBody.Builder();

        for (File file : files) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            builder.addFormDataPart("file", file.getName(), requestBody);
        }

        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        return multipartBody;
    }

    public static List<MultipartBody.Part> filesToParts(List<File> files){
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for(File file : files){
            RequestBody requestBody = RequestBody.create(MediaType.parse("img/png"), file);

            MultipartBody.Part part = MultipartBody.Part.createFormData("file" , file.getName() , requestBody);
            parts.add(part);
        }

        return parts;
    }
}

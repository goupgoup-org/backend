package com.goupgoup_backend.aws.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.goupgoup_backend.aws.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AwsS3Service {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


    public List<String> upload(List<MultipartFile> multipartFileList) {
        List<String> imgUrlList = new ArrayList<>();

        for (MultipartFile file : multipartFileList) {
            String fileName = FileUtil.convert(file);

            try {
                amazonS3Client.putObject(bucket, fileName, file.getInputStream(), null);
                imgUrlList.add(amazonS3Client.getUrl(bucket, fileName).toString());
            } catch (Exception e) {
                throw new RuntimeException("파일 업로드 실패");
            }
        }

        return imgUrlList;
    }

    public void delete(String fileName) {
        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }

}

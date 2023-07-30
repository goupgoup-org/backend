package com.example.client.aws.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.goupgoup_backend.aws.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class AwsS3Service {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


    public String upload(MultipartFile multipartFile, String directory) {
        String imgUrlList;

        String fileName = FileUtil.convert(multipartFile);

        if(directory != null) fileName = directory + "/" + fileName;

        try {
            amazonS3Client.putObject(bucket, fileName, multipartFile.getInputStream(), null);
            imgUrlList = amazonS3Client.getUrl(bucket, fileName).toString();
        } catch (Exception e) {
            throw new RuntimeException("파일 업로드 실패");
        }

        return imgUrlList;
    }


    public void delete(String fileName) {
        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }

}

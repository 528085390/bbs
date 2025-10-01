package com.li.bbs.util;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.*;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class OssUtil {

    private static final String ENDPOINT = "https://oss-cn-guangzhou.aliyuncs.com";
    private static final String REGION = "cn-guangzhou";
    private static final String BUCKET_NAME = "gduf-bbs";

    private static volatile OSS ossClient;

    /**
     * 获取OSS客户端实例
     * @return OSS客户端实例
     */
    private static OSS getOssClient() throws com.aliyuncs.exceptions.ClientException {
        if (ossClient == null) {
            synchronized (OssUtil.class) {
                if (ossClient == null) {
                    EnvironmentVariableCredentialsProvider credentialsProvider =
                        CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

                    ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
                    clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);

                    ossClient = OSSClientBuilder.create()
                            .endpoint(ENDPOINT)
                            .credentialsProvider(credentialsProvider)
                            .clientConfiguration(clientBuilderConfiguration)
                            .region(REGION)
                            .build();
                }
            }
        }
        return ossClient;
    }

    /**
     * 上传文件到OSS(自动按日期分类并生成唯一文件名)
     * @param originalFileName 原始文件名(用于获取文件扩展名)
     * @param file 要上传的文件
     * @return 文件访问URL
     * @throws OSSException OSS异常
     * @throws ClientException 客户端异常
     * @throws com.aliyuncs.exceptions.ClientException 凭证异常
     */
    public static String uploadFileAuto(String originalFileName, File file)
            throws OSSException, ClientException, com.aliyuncs.exceptions.ClientException {
        String objectName = generateAutoPath(originalFileName);
        return uploadFile(BUCKET_NAME, objectName, file);
    }

    /**
     * 上传InputStream到OSS(自动按日期分类并生成唯一文件名)
     * @param originalFileName 原始文件名(用于获取文件扩展名)
     * @param inputStream 文件输入流
     * @return 文件访问URL
     * @throws OSSException OSS异常
     * @throws ClientException 客户端异常
     * @throws com.aliyuncs.exceptions.ClientException 凭证异常
     */
    public static String uploadFileAuto(String originalFileName, InputStream inputStream)
            throws OSSException, ClientException, com.aliyuncs.exceptions.ClientException {
        String objectName = generateAutoPath(originalFileName);
        return uploadFile(BUCKET_NAME, objectName, inputStream);
    }

    /**
     * 生成自动分类的文件路径(按日期分类+唯一文件名)
     * @param originalFileName 原始文件名
     * @return 自动生成的OSS对象路径
     */
    private static String generateAutoPath(String originalFileName) {
        // 按日期生成目录
        String datePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());

        // 生成唯一文件名
        String uniqueFileName = UUID.randomUUID().toString().replace("-", "");

        // 保留原始文件扩展名
        String extension = "";
        if (originalFileName != null && originalFileName.lastIndexOf(".") != -1) {
            extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }

        return datePath + "/" + uniqueFileName + extension;
    }

    /**
     * 上传文件到OSS
     * @param objectName OSS上的文件路径和名称
     * @param file 要上传的文件
     * @return 文件访问URL
     * @throws OSSException OSS异常
     * @throws ClientException 客户端异常
     * @throws com.aliyuncs.exceptions.ClientException 凭证异常
     */
    public static String uploadFile(String objectName, File file)
            throws OSSException, ClientException, com.aliyuncs.exceptions.ClientException {
        return uploadFile(BUCKET_NAME, objectName, file);
    }

    /**
     * 上传文件到指定Bucket
     * @param bucketName Bucket名称
     * @param objectName OSS上的文件路径和名称
     * @param file 要上传的文件
     * @return 文件访问URL
     * @throws OSSException OSS异常
     * @throws ClientException 客户端异常
     * @throws com.aliyuncs.exceptions.ClientException 凭证异常
     */
    public static String uploadFile(String bucketName, String objectName, File file)
            throws OSSException, ClientException, com.aliyuncs.exceptions.ClientException {

        OSS client = getOssClient();
        try {
            // 创建PutObjectRequest对象
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, file);

            // 上传文件
            PutObjectResult result = client.putObject(putObjectRequest);

            // 构造并返回文件URL
            return generateFileUrl(bucketName, objectName);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
            throw oe;
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
            throw ce;
        }
    }

    /**
     * 上传InputStream到OSS
     * @param objectName OSS上的文件路径和名称
     * @param inputStream 文件输入流
     * @return 文件访问URL
     * @throws OSSException OSS异常
     * @throws ClientException 客户端异常
     * @throws com.aliyuncs.exceptions.ClientException 凭证异常
     */
    public static String uploadFile(String objectName, InputStream inputStream)
            throws OSSException, ClientException, com.aliyuncs.exceptions.ClientException {
        return uploadFile(BUCKET_NAME, objectName, inputStream);
    }

    /**
     * 上传InputStream到指定Bucket
     * @param bucketName Bucket名称
     * @param objectName OSS上的文件路径和名称
     * @param inputStream 文件输入流
     * @return 文件访问URL
     * @throws OSSException OSS异常
     * @throws ClientException 客户端异常
     * @throws com.aliyuncs.exceptions.ClientException 凭证异常
     */
    public static String uploadFile(String bucketName, String objectName, InputStream inputStream)
            throws OSSException, ClientException, com.aliyuncs.exceptions.ClientException {

        OSS client = getOssClient();
        try {
            // 创建PutObjectRequest对象
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);

            // 上传文件
            PutObjectResult result = client.putObject(putObjectRequest);

            // 构造并返回文件URL
            return generateFileUrl(bucketName, objectName);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
            throw oe;
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
            throw ce;
        }
    }

    /**
     * 生成文件访问URL
     * @param bucketName Bucket名称
     * @param objectName 文件名称
     * @return 文件访问URL
     */
    private static String generateFileUrl(String bucketName, String objectName) {
        return ENDPOINT.replace("https://", "https://" + bucketName + ".") + "/" + objectName;
    }

    /**
     * 关闭OSS客户端
     */
    public static void shutdown() {
        if (ossClient != null) {
            ossClient.shutdown();
            ossClient = null;
        }
    }
}


package com.example.martin.mymvp.net;


import com.example.martin.mymvp.base.BaseBean;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;


/**
 * 网络访问接口
 */
public interface ApiService {
    /**
     * 获取验证码
     * 类型（1.注册短信 2.登录短信 3.忘记密码）
     */
    @FormUrlEncoded
    @POST("/mobile/sms/sendMobileSms")
    Observable<BaseBean> sendMobileSms(@Header("source") String source, @FieldMap Map<String, String> map);

    /**
     *上传图片
     */
    @Multipart
    @POST("/mobile/auth/ocrRecognition")
    Observable<BaseBean> ocrRecognition(@Header("source") String source, @Part MultipartBody.Part file);

}
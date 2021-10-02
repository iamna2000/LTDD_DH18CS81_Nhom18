package com.example.messapp.Fragment;

import com.example.messapp.Notifications.Response;
import com.example.messapp.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIs {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAA4gqCSaA:APA91bGPIcWIxonJKgnsJ610D2seoTI8vYJ1ketVYFn7QssqFvfLS5oHuEdVaGEuGgebzNAwkko_gnrAyWGjlY8iFJnUkjh5mrJm1fruWdaOPkKpfMuDR-aEuNKTLn3f5plHnCVTmYx3"
            }
    )

    @POST("fcm/send")
    Call<Response> sendNotification(@Body Sender body);
}

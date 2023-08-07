package com.Retrofit.packgs.model;

import com.Retrofit.packgs.api.ApiController;
import com.Retrofit.packgs.interfaces.ProcessCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.nio.charset.StandardCharsets;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Operation implements Serializable {
  private String date;

  private Actor actor;

  private String amount;

  private String category_name;

  private String category_id;

  private String admin_id;

  private String details;

  public Integer id;

  private String actor_id;

  public String getDate() {
    return this.date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public Actor getActor() {
    return this.actor;
  }

  public void setActor(Actor actor) {
    this.actor = actor;
  }

  public String getAmount() {
    return this.amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getCategory_name() {
    return this.category_name;
  }

  public void setCategory_name(String category_name) {
    this.category_name = category_name;
  }

  public String getCategory_id() {
    return this.category_id;
  }

  public void setCategory_id(String category_id) {
    this.category_id = category_id;
  }

  public String getAdmin_id() {
    return this.admin_id;
  }

  public void setAdmin_id(String admin_id) {
    this.admin_id = admin_id;
  }

  public String getDetails() {
    return this.details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getActor_id() {
    return this.actor_id;
  }

  public void setActor_id(String actor_id) {
    this.actor_id = actor_id;
  }

  public static class Actor implements Serializable {
    private String gender;

    private String image_url;

    private String name;

    private String mobile;

    private String family_members;

    private String tower_name;

    private Integer id;

    private String national_number;

    private String email;

    public String getGender() {
      return this.gender;
    }

    public void setGender(String gender) {
      this.gender = gender;
    }

    public String getImage_url() {
      return this.image_url;
    }

    public void setImage_url(String image_url) {
      this.image_url = image_url;
    }

    public String getName() {
      return this.name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getMobile() {
      return this.mobile;
    }

    public void setMobile(String mobile) {
      this.mobile = mobile;
    }

    public String getFamily_members() {
      return this.family_members;
    }

    public void setFamily_members(String family_members) {
      this.family_members = family_members;
    }

    public String getTower_name() {
      return this.tower_name;
    }

    public void setTower_name(String tower_name) {
      this.tower_name = tower_name;
    }

    public Integer getId() {
      return this.id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public String getNational_number() {
      return this.national_number;
    }

    public void setNational_number(String national_number) {
      this.national_number = national_number;
    }

    public String getEmail() {
      return this.email;
    }

    public void setEmail(String email) {
      this.email = email;
    }
  }

    public void deleteOperation(ProcessCallBack processCallBack) {
    Call<BaseResponse> call = ApiController.getInstance().getRetrofitRequest().deleteOperation(id);
    call.enqueue(new Callback<BaseResponse>() {
      @Override
      public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
        if (response.isSuccessful()) {
          processCallBack.onSuccess(response.body().message);
        } else {
          try {
            String error = new String(response.errorBody().bytes(), StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(error);
            processCallBack.onFailure(jsonObject.getString("message"));

          } catch (IOException | JSONException e) {
            e.printStackTrace();
          }
        }

      }

      @Override
      public void onFailure(Call<BaseResponse> call, Throwable t) {

      }
    });
  }

}
package com.mvp.cifer.wanandroid.Login;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mvp.cifer.wanandroid.BuildConfig;
import com.mvp.cifer.wanandroid.MainAgent;
import com.mvp.cifer.wanandroid.R;
import com.mvp.cifer.wanandroid.basemvp.BaseActivity;

import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * - @author :  Xiao
 * - @date   :  2018/10/30
 * - @time   :  17:10
 * - @desc   :
 */
public class LoginActivity extends BaseActivity<LoginContract.LoginView,LoginPresenter> implements LoginContract.LoginView,View.OnClickListener{
    @BindView(R.id.loginNameTV)
    EditText nametext;

    @BindView(R.id.loginPasswordTv)
    EditText passwdtext;

    @BindView(R.id.btnGoLogin)
    TextView login;

    public static void openLoginActivity(Context context){
        Intent intent = new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected LoginContract.LoginView createView() {
        return this;
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public LoginPresenter getPresenter() {
        return super.getPresenter();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_act);

        ButterKnife.bind(this);

        /*
         * 可以根据build.gradle中的productflavors 配置不同BASEIPTYPE
         * 满足不同环境项目需求
         * 比如网络 13 105 ..
         */
        Log.d("xiao333"," Build type == " + BuildConfig.BUILD_TYPE);
        Log.d("xiao333"," Build type int == " + BuildConfig.BASEIPTYPE);

        login.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void go() {
        /*Intent intent = new Intent(this, MainAgent.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());*/
        MainAgent.openMainAgent(this);
        this.finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGoLogin:
                getPresenter().getLoginB(Objects.requireNonNull(nametext.getText()).toString(), Objects.requireNonNull(passwdtext.getText()).toString());
                break;
        }
    }


    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}

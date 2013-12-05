package com.msplearning.android;

import java.util.Date;

import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.rest.RestService;
import com.msplearning.android.compatibility.interoperability.StudentRESTfulClient;
import com.msplearning.entity.Gender;
import com.msplearning.entity.Student;

@EActivity(R.layout.activity_login)
public class HelloAndroidActivity extends SherlockActivity {

	@ViewById(R.id.editTextEmail)
	EditText editTextEmail;

	@ViewById(R.id.editTextPassword)
	EditText editTextPassword;

	@RestService
	StudentRESTfulClient restClient;

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater();
        return true;
    }

	@Click(R.id.buttonLogin)
	void onLoginClick() {
		Student student = new Student();
		student.setDateBirth(new Date());
		student.setDateLastLogin(new Date());
		student.setDateRegistration(new Date());
		student.setFirstName("Venilton");
		student.setGender(Gender.M);
		student.setLastName("Falvo Jr.");
		student.setPassword("android");
		student.setUsername("veniltonjr");

		doSomethingInBackground(student);
	}
	
    @Background
    void doSomethingInBackground(Student student) {
        this.restClient.insert(student);
        doSomethingElseOnUiThread();
    }
	
    @UiThread
    void doSomethingElseOnUiThread() {
        Toast.makeText(this.getApplicationContext(), "Serviço consumo com sucesso!", Toast.LENGTH_LONG);
    }

}

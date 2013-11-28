package com.example.lesson7;

import database.Student;
import android.annotation.SuppressLint;
import android.app.Activity;

@SuppressLint("ValidFragment")
public class StudentEditDialog extends StudentDialog
{
    public interface EditStudentDialogListener {
        public void onDialogPositiveClick(Student student);
    }
    
    private EditStudentDialogListener       mListener;
    
	public StudentEditDialog(Student student) {
		super(student);
	}
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (EditStudentDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement EditStudentDialogListener");
        }
    }
        
    @Override
    protected void clickPositive() {
    	mListener.onDialogPositiveClick(mStudent);
    }
}
package com.example.lesson7;

import database.Group;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

@SuppressLint("ValidFragment")
public class GroupDialog extends DialogFragment
{
    public interface EditGroupDialogListener {
        public void onDialogPositiveClick(Group group);
    }
    
    private EditText                   		mNameEditText;
    private EditText                   		mSemesterEditText;
    
    private EditGroupDialogListener       	mListener;
    private final Group 					mGroup;
    
	public GroupDialog(Group group) {
		this.mGroup = group;
	}
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (EditGroupDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement EditGroupDialogListener");
        }
    }
        
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        
        LayoutInflater inflater = getActivity().getLayoutInflater();
        
        final View view = inflater.inflate(R.layout.group_dialog, null);
        builder.setView(view);
        
        mNameEditText   = (EditText)view.findViewById(R.id.groupName);
        mSemesterEditText   = (EditText)view.findViewById(R.id.groupSemester);
        
        mNameEditText.setText(mGroup.name);
        mSemesterEditText.setText(mGroup.semester.toString());
        
        builder.setMessage("Dialog")
               .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   updateGroup();		
                	   mListener.onDialogPositiveClick(mGroup);
                   }
               })
               .setNegativeButton("Cancel", null);
        
        return builder.create();
    }
    
    private void updateGroup()
    {
    	mGroup.name = mNameEditText.getText().toString();
    	mGroup.semester = Integer.parseInt(mSemesterEditText.getText().toString());
    }
}
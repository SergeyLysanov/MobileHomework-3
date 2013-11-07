package com.example.lesson7;

import services.StudentUpdateService;
import services.GroupUpdateService;

import database.Group;
import database.GroupsContract.GroupEntry;
import database.Student;
import database.StudentsContract.StudentEntry;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends FragmentActivity 
							implements StudentDialog.EditStudentDialogListener,
							GroupDialog.EditGroupDialogListener{
	
	private int mCurrentTab = 0;
	
	private static final String STATE_TAB = "SELECTED_TAB";
	private static final int STUDENTS_TAB = 0;
	private static final int GROUPS_TAB = 1;
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

    	//Create action bar
        final ActionBar bar = getActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    	bar.setDisplayShowHomeEnabled(false);
    	bar.setDisplayShowTitleEnabled(false);
        bar.addTab(bar.newTab().setText("Студенты").setTabListener(new TabListener()), false);
        bar.addTab(bar.newTab().setText("Группы").setTabListener(new TabListener()), false);    

    	if (savedInstanceState != null) {
    		mCurrentTab = savedInstanceState.getInt(STATE_TAB);
        } 
    	bar.setSelectedNavigationItem(mCurrentTab);
		
    	setupFragment();
	}
	
	private void setupFragment(){
		Fragment fragment = null;
    	if(mCurrentTab == STUDENTS_TAB){
    		fragment = new StudentsFragment();
    	}
    	else if(mCurrentTab == GROUPS_TAB){
    		fragment = new GroupsFragment();
    	}
    	
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(STATE_TAB, mCurrentTab);
	}
	
	private class TabListener implements ActionBar.TabListener {

		@Override
		public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {}

		@Override
		public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
			mCurrentTab = tab.getPosition();
			setupFragment();
		}

		@Override
		public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
		}
    }

	@Override
	public void onDialogPositiveClick(Student student) {
		 Intent intent = new Intent(MainActivity.this, StudentUpdateService.class);
         intent.putExtra(StudentEntry._ID, student.id);
         intent.putExtra(StudentEntry.COLUMN_NAME_STUDENT_NAME, student.name);
         intent.putExtra(StudentEntry.COLUMN_NAME_STUDENT_SURNAME, student.surname); 
         intent.putExtra(StudentEntry.COLUMN_NAME_GROUP, student.groupId);
         
         startService(intent);
	}

	@Override
	public void onDialogPositiveClick(Group group) {
		 Intent intent = new Intent(MainActivity.this, GroupUpdateService.class);
         intent.putExtra(GroupEntry._ID, group.id);
         intent.putExtra(GroupEntry.COLUMN_NAME_GROUP_NAME, group.name);
         intent.putExtra(GroupEntry.COLUMN_NAME_SEMESTER, group.semester); 
         
         startService(intent);
	}
}

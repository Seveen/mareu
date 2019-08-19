package com.guilhempelissier.mareu.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

@RunWith(JUnit4.class)
public class MeetingListViewModelTest {

	private MeetingListViewModel vm;

	@Rule
	public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

	@Before
	public void setup() {
		vm = new MeetingListViewModel();
	}

	@Test
	public void getMeetingListFilterWithSuccess() {
		MeetingListFilter expectedFilter = new MeetingListFilter(0,0,new ArrayList<>());
		MeetingListFilter actualFilter = vm.getMeetingListFilter().getValue();
		assertEqual(actualFilter, expectedFilter);
	}
}

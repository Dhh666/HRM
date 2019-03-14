package com.company.hrm.common;

public class ErrMsg {
	public static interface userErr{
		String LOGIN_ERROR = "lonin error!";
		String DUPLICATE_USERNAME = "ע��ʧ��!�û����ظ�!";
		String REGIST_EMPTY_ERROR = "ע��ʧ��!�����Ƿ���δ������!";
		String REGIST_PASSWORD_ERROR = "�������벻һ��,����!";
		String REGIST_ERROR = "ע��ʧ��!����������!";
	}
	public static interface empErr{
		String NO_PRIORITY = "�޲鿴Ȩ��,�����µ�¼!";
		String FINDALL_ERROR = "δ֪������б�Ϊ��!";
		String SAVE_ERROR = "�½�Ա��ʧ��";
		String DELETE_ERROR = "ɾ��Ա��ʧ��";
		String FIND_BY_ID_ERROR = "����Ա��IDʧ��!";
		String UPDATE_ERROR = "����ʧ��";
		String FIND_BY_NAME_ERROR = "����Ա������ʧ��!";
		
	}
	public static interface deptErr{
		
	}
}

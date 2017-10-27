/**
 * 
 */
package com.base.constants;

/**
 * 权限初始化常量
 * 
 * @author zjn
 * @date 2016年10月28日
 */
public class PermissionConstants {

	public static final String travel = "travelPer";
	public static final String busNeed = "busNeedPer";
	public static final String ad = "adPer";
	public static final String smallGoods = "smallGoodsPer";
	public static final String truckLoad = "truckLoadPer";
	public static final String system = "systemPer";
	public static final String index = "indexPer";
	public static final String left = "leftPer";

	// 旅游所有权限:{新建、修改、删除}
	public static final String[] travelPer = { "tAdd", "tEdit", "tDel" };

	// 班车预订:{补录、删除}
	public static final String[] busNeedPer = { "bnEdit", "bnDel" };

	// 广告所有权限:{审核、删除}
	public static final String[] adPer = { "adAudit", "adDel" };
	
	// 小件快运所有权限:{补录 ,删除}
	public static final String[] smallGoodsPer = { "sgEdit", "sgDel"};

	// 零担货运所有权限:{审核, 删除}
	public static final String[] truckLoadPer = { "tlAudit", "tlDel"};

	// 用户管理所有权限:{添加角色 ,删除角色, 修改角色, 添加用户, 删除用户, 修改用户}
	public static final String[] systemPer = { "uRoleAdd", "uRoleDel", "uRoleEdit", "uUserAdd", "uUserDel",
			"uUserEdit"};
	// 首页显示所有权限:{文书任务, 补录合同任务, 审核发票, 完成发票, 核对到款, 收款超时 ,工期超时}
	public static final String[] indexPer = { "iAssiTask", "iEditTask", "iAudiInvoTask", "iFiniInvoTask",
			"iFiniRemoTask", "iDebtAlarm", "iOverdueAlarm" };

	// 左侧功能栏所有权限:{合同管理, 票据管理, 用户管理，发票任务, 到款任务, 收款超时 ,工期超时,报表统计}
	public static final String[] leftPer = { "system", "travel", "busNeed", "ad", "smallGoods",
			"truckLoad"};

}

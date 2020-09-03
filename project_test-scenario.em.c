#include <opnet.h>
#include <ema.h>
#include <opnet_emadefs.h>
#include <opnet_constants.h>
# include <stdlib.h>


/* array for all textlist attributes in model */
Prg_List*		prg_lptr [5];

/* array for all objects in model */
EmaT_Object_Id		obj [10];

# define NODE_NUMBER 300  /* 节点数量 */
# define X_MIN 9468.45    /* 场景大小,可以采用SUMO_Pretreatment.java输出区域的范围 */
# define X_MAX 13047.56
# define Y_MIN 12293.16
# define Y_MAX 16284.85
# define X_LENGTH 3579.11
# define Y_LENGTH 3991.25                            
# define MIN_DISTANCE_2 400  /* 节点最小距离的平方, 这个值越大, 节点生成越均匀, 这个值理论最大为25*25*1.414, 值越大生成的时间越长 */
int PosChooseAgain;  /* 是否重新生成 */
int NodeNumber;      /* 节点数 */
int i, k;            /* 循环变量 */   
char node_name[5];   /* 节点名称 */
char tra[20];        /* 轨迹名称 */
double x_pos,y_pos;   /* 节点坐标 */
double NodeXList[300], NodeYList[300]; /* 节点坐标数组 */
EmaT_Object_Id wsn_node_objid; /* 对象变量 */

int
main (int argc, char* argv [])
	{
	EmaT_Model_Id			model_id;
	int					i;

	/* initialize EMA package */
	Ema_Init (EMAC_MODE_ERR_PRINT | EMAC_MODE_REL_60, argc, argv);

	/* create an empty model */
	model_id = Ema_Model_Create (MOD_NETWORK);



	/* create all objects */
	obj [0] = Ema_Object_Create (model_id, OBJ_NT_SUBNET_FIX);
	obj [1] = Ema_Object_Create (model_id, OBJ_NT_SUBNET_VIEW);
	obj [2] = Ema_Object_Create (model_id, OBJ_NT_SUBNET_FIX);
	obj [3] = Ema_Object_Create (model_id, OBJ_NT_SUBNET_VIEW);
	//obj [4] = Ema_Object_Create (model_id, OBJ_NT_NODE_MOBILE);
	obj [5] = Ema_Object_Create (model_id, OBJ_NT_ISO_ELEV_MAP_COLOR_SETTING);
	obj [6] = Ema_Object_Create (model_id, OBJ_NT_ISO_ELEV_MAP_COLOR_SETTING);
	obj [7] = Ema_Object_Create (model_id, OBJ_NT_ISO_ELEV_MAP_COLOR_SETTING);
	obj [8] = Ema_Object_Create (model_id, OBJ_NT_ISO_ELEV_MAP_COLOR_SETTING);
	obj [9] = Ema_Object_Create (model_id, OBJ_NT_ISO_ELEV_MAP_COLOR_SETTING);




	/* set the model level attributes */
	/* create and init prg list 'prg_lptr [0]' */
	prg_lptr [0] = (Prg_List *)prg_list_create ();
	/* create and init prg list 'prg_lptr [1]' */
	prg_lptr [1] = (Prg_List *)prg_list_create ();
	prg_list_strings_append (prg_lptr [1], 
		"custom_model_list",
		"internet_toolbox",
		PRGC_NIL);

	/* create and init prg list 'prg_lptr [2]' */
	prg_lptr [2] = (Prg_List *)prg_list_create ();
	Ema_Model_Attr_Set (model_id,
		"ext fileset",          COMP_CONTENTS, prg_lptr [0],
		"keywords list",        COMP_CONTENTS, prg_lptr [1],
		"view subnet",          COMP_CONTENTS, obj [2],
		"iso elev map color levels",COMP_ARRAY_CONTENTS (0), obj [5],
		"iso elev map color levels",COMP_ARRAY_CONTENTS (1), obj [6],
		"iso elev map color levels",COMP_ARRAY_CONTENTS (2), obj [7],
		"iso elev map color levels",COMP_ARRAY_CONTENTS (3), obj [8],
		"iso elev map color levels",COMP_ARRAY_CONTENTS (4), obj [9],
		"iso elev map label color",COMP_CONTENTS, 0,
		"view index list",      COMP_CONTENTS, prg_lptr [2],
		EMAC_EOL);



	/* assign attrs for object 'obj [0]' */
	/* create and init prg list 'prg_lptr [3]' */
	prg_lptr [3] = (Prg_List *)prg_list_create ();
	Ema_Object_Attr_Set (model_id, obj [0], 
		"name",                 COMP_CONTENTS, "top",
		"name",                 COMP_USER_INTENDED, EMAC_ENABLED,
		"x position",           COMP_CONTENTS, (double) 0,
		"x position",           COMP_USER_INTENDED, EMAC_ENABLED,
		"y position",           COMP_CONTENTS, (double) 0,
		"y position",           COMP_USER_INTENDED, EMAC_ENABLED,
		"x span",               COMP_CONTENTS, (double) 360,
		"x span",               COMP_USER_INTENDED, EMAC_ENABLED,
		"y span",               COMP_CONTENTS, (double) 180,
		"y span",               COMP_USER_INTENDED, EMAC_ENABLED,
		"icon name",            COMP_CONTENTS, "subnet",
		"icon name",            COMP_INTENDED, EMAC_DISABLED,
		"map",                  COMP_CONTENTS, "world",
		"map",                  COMP_USER_INTENDED, EMAC_ENABLED,
		"subnet",               COMP_CONTENTS, EMAC_NULL_OBJ_ID,
		"view stack",           COMP_ARRAY_CONTENTS (0), obj [1],
		EMAC_EOL);

	Ema_Object_Attr_Set (model_id, obj [0], 
		"grid unit",            COMP_CONTENTS, 5,
		"ui status",            COMP_CONTENTS, 0,
		"iso-elev-map list",    COMP_CONTENTS, prg_lptr [3],
		"iso-elev-map line threshold",COMP_CONTENTS, (double) 50,
		"view",                 COMP_CONTENTS, "Default View",
		"view mode",            COMP_CONTENTS, 0,
		"view positions",       COMP_INTENDED, EMAC_DISABLED,
		EMAC_EOL);


	/* assign attrs for object 'obj [2]' */
	/* create and init prg list 'prg_lptr [4]' */
	prg_lptr [4] = (Prg_List *)prg_list_create ();
	Ema_Object_Attr_Set (model_id, obj [2], 
		"name",                 COMP_CONTENTS, "Campus Network",
		"name",                 COMP_USER_INTENDED, EMAC_ENABLED,
		"user id",              COMP_CONTENTS, 0,
		"user id",              COMP_USER_INTENDED, EMAC_ENABLED,
		"x position",           COMP_CONTENTS, (double) 0,
		"x position",           COMP_USER_INTENDED, EMAC_ENABLED,
		"y position",           COMP_CONTENTS, (double) 0,
		"y position",           COMP_USER_INTENDED, EMAC_ENABLED,
		"x span",               COMP_CONTENTS, (double) 5.54715743194246,
		"x span",               COMP_USER_INTENDED, EMAC_ENABLED,
		"y span",               COMP_CONTENTS, (double) 35.9326113647809,
		"y span",               COMP_USER_INTENDED, EMAC_ENABLED,
		"priority",             COMP_CONTENTS, 0,
		"priority",             COMP_USER_INTENDED, EMAC_ENABLED,
		"outline color",        COMP_CONTENTS, 3,
		"outline color",        COMP_USER_INTENDED, EMAC_ENABLED,
		EMAC_EOL);

	Ema_Object_Attr_Set (model_id, obj [2], 
		"icon name",            COMP_CONTENTS, "subnet",
		"icon name",            COMP_INTENDED, EMAC_DISABLED,
		"icon name",            COMP_USER_INTENDED, EMAC_ENABLED,
		"map",                  COMP_CONTENTS, "NONE",
		"map",                  COMP_USER_INTENDED, EMAC_ENABLED,
		"subnet",               COMP_CONTENTS, obj [0],
		"view stack",           COMP_ARRAY_CONTENTS (0), obj [3],
		"grid unit",            COMP_CONTENTS, 1,
		"ui status",            COMP_CONTENTS, 0,
		"iso-elev-map list",    COMP_CONTENTS, prg_lptr [4],
		"iso-elev-map line threshold",COMP_CONTENTS, (double) 50,
		"view",                 COMP_CONTENTS, "Default View",
		"view mode",            COMP_CONTENTS, 0,
		"view positions",       COMP_INTENDED, EMAC_DISABLED,
		EMAC_EOL);

    NodeNumber=0;
	for (k=1; k <= NODE_NUMBER; k++){
	sprintf(node_name, "%d", k); /*节点依次命名" 0,1,2,3,....,NODE_NUMBER" */
	sprintf(tra, "10ms_2_Real_SUMO_%d", k); /*节点轨迹依则以SUMO_To_OPNET_Trj.java最终生成的轨迹文件名称为准*/
	PosChooseAgain = 1;  
	while(PosChooseAgain == 1) /* 随机生成节点坐标*/
	{   
	   x_pos= ((double)rand()/((double)(RAND_MAX) + (double)(1)))*(double)X_LENGTH + X_MIN; 
       y_pos= ((double)rand()/((double)(RAND_MAX) + (double)(0)))*(double)Y_LENGTH + Y_MIN;
      for (i=0; i< NodeNumber; i++) 
	  {
	    if (((NodeXList[i]- x_pos)*(NodeXList[i]- x_pos)+(NodeYList[i]- y_pos)*(NodeYList[i]- y_pos))< MIN_DISTANCE_2) 
	    {
           break;	
	    }
	 }	
		
		if(i == NodeNumber) { 
          PosChooseAgain = 0;  
		}
	}	
		
	NodeNumber++;
    NodeXList[NodeNumber-1] = x_pos;
    NodeYList[NodeNumber-1] = y_pos;
	wsn_node_objid= Ema_Object_Create(model_id, OBJ_NT_NODE_MOBILE); /*创建一个节点*/
    /*下面是刚才obj[4]的代码, 将其中节点名称和纵横坐标替换*/
	Ema_Object_Attr_Set (model_id, wsn_node_objid, 
		"name",                 COMP_CONTENTS, node_name,
		"name",                 COMP_USER_INTENDED, EMAC_ENABLED,
		"model",                COMP_CONTENTS, "apr_node_model",
		"model",                COMP_USER_INTENDED, EMAC_ENABLED,
		"x position",           COMP_CONTENTS, (double) x_pos,
		"x position",           COMP_USER_INTENDED, EMAC_ENABLED,
		"y position",           COMP_CONTENTS, (double) y_pos,
		"y position",           COMP_USER_INTENDED, EMAC_ENABLED,
		"trajectory",           COMP_CONTENTS, tra,
		"trajectory",           COMP_INTENDED, EMAC_DISABLED,
		"trajectory",           COMP_USER_INTENDED, EMAC_ENABLED,
		"ground speed",         COMP_CONTENTS, "",
		"ground speed",         COMP_INTENDED, EMAC_DISABLED,
		"ground speed",         COMP_USER_INTENDED, EMAC_ENABLED,
		"ascent rate",          COMP_CONTENTS, "",
		"ascent rate",          COMP_INTENDED, EMAC_DISABLED,
		EMAC_EOL);

	Ema_Object_Attr_Set (model_id, wsn_node_objid, 
		"ascent rate",          COMP_USER_INTENDED, EMAC_ENABLED,
		"color",                COMP_CONTENTS, 1090519039,
		"color",                COMP_INTENDED, EMAC_DISABLED,
		"color",                COMP_USER_INTENDED, EMAC_ENABLED,
		"doc file",             COMP_CONTENTS, "",
		"doc file",             COMP_INTENDED, EMAC_DISABLED,
		"doc file",             COMP_USER_INTENDED, EMAC_ENABLED,
		"subnet",               COMP_CONTENTS, obj [2],
		"alias",                COMP_INTENDED, EMAC_DISABLED,
		"tooltip",              COMP_CONTENTS, "",
		"tooltip",              COMP_INTENDED, EMAC_DISABLED,
		"tooltip",              COMP_USER_INTENDED, EMAC_ENABLED,
		"ui status",            COMP_CONTENTS, 512,
		"view positions",       COMP_INTENDED, EMAC_DISABLED,
		EMAC_EOL);

    }

	/* assign attrs for object 'obj [1]' */
	Ema_Object_Attr_Set (model_id, obj [1], 
		"min x",                COMP_CONTENTS, (double) -180,
		"min y",                COMP_CONTENTS, (double) 90,
		"sbar x",               COMP_CONTENTS, (double) 0,
		"sbar y",               COMP_CONTENTS, (double) 0,
		"grid step",            COMP_CONTENTS, (double) 15,
		"resolution",           COMP_CONTENTS, (double) 2.5,
		"grid style",           COMP_CONTENTS, 2,
		"grid color",           COMP_CONTENTS, 21,
		EMAC_EOL);


	/* assign attrs for object 'obj [3]' */
	Ema_Object_Attr_Set (model_id, obj [3], 
		"min x",                COMP_CONTENTS, (double) 0,
		"min y",                COMP_CONTENTS, (double) 0,
		"sbar x",               COMP_CONTENTS, (double) 0,
		"sbar y",               COMP_CONTENTS, (double) 0,
		"grid step",            COMP_CONTENTS, (double) 500,
		"resolution",           COMP_CONTENTS, (double) 0.147503369652425,
		"grid style",           COMP_CONTENTS, 2,
		"grid color",           COMP_CONTENTS, 21,
		EMAC_EOL);


	/* assign attrs for object 'obj [5]' */
	Ema_Object_Attr_Set (model_id, obj [5], 
		"elevation",            COMP_CONTENTS, (double) 1e+100,
		"color",                COMP_CONTENTS, 1090519039,
		EMAC_EOL);


	/* assign attrs for object 'obj [6]' */
	Ema_Object_Attr_Set (model_id, obj [6], 
		"elevation",            COMP_CONTENTS, (double) 5000,
		"color",                COMP_CONTENTS, 1073741824,
		EMAC_EOL);


	/* assign attrs for object 'obj [7]' */
	Ema_Object_Attr_Set (model_id, obj [7], 
		"elevation",            COMP_CONTENTS, (double) 3000,
		"color",                COMP_CONTENTS, 1086806624,
		EMAC_EOL);


	/* assign attrs for object 'obj [8]' */
	Ema_Object_Attr_Set (model_id, obj [8], 
		"elevation",            COMP_CONTENTS, (double) 1000,
		"color",                COMP_CONTENTS, 1073745152,
		EMAC_EOL);


	/* assign attrs for object 'obj [9]' */
	Ema_Object_Attr_Set (model_id, obj [9], 
		"elevation",            COMP_CONTENTS, (double) 1,
		"color",                COMP_CONTENTS, 1079591136,
		EMAC_EOL);








	/* write the model to application-readable form */
	Ema_Model_Write (model_id, "Test");

	return 0;
	}
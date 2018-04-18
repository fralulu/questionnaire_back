/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/10/30 11:22:25                          */
/*==============================================================*/


drop table if exists iot_bas_alarmoperation;

drop table if exists iot_bas_alminfoext;

drop table if exists iot_bas_changesubject;

drop table if exists iot_bas_devicekind;

drop table if exists iot_bas_devicemodel;

drop table if exists iot_bas_devicestatus;

drop table if exists iot_bas_devicetemplate;

drop table if exists iot_bas_devicetype;

drop table if exists iot_bas_devstatusmap;

drop table if exists iot_bas_manufacturer;

drop table if exists iot_bas_masktype;

drop table if exists iot_bas_metekind;

drop table if exists iot_bas_nettype;

drop table if exists iot_bas_reportkind;

drop table if exists iot_bas_reportlevel;

drop table if exists iot_bas_reporttype;

drop table if exists iot_bas_resultcode;

drop table if exists iot_bas_runtype;

drop table if exists iot_bas_signalkind;

drop table if exists iot_bas_sitekind;

drop table if exists iot_bas_sitelevel;

drop table if exists iot_bas_storespan;

drop table if exists iot_bas_teleadjust;

drop table if exists iot_bas_telecontrol;

drop table if exists iot_bas_telemeter;

drop table if exists iot_bas_telesignal;

drop table if exists iot_bas_telestrategy;

drop table if exists iot_bas_useroperation;

drop table if exists iot_cfg_alarmmask;

drop table if exists iot_cfg_alminfoext;

drop table if exists iot_cfg_building;

drop table if exists iot_cfg_device;

drop table if exists iot_cfg_devicestatus;

drop table if exists iot_cfg_devstatusmap;

drop table if exists iot_cfg_express;

drop table if exists iot_cfg_floor;

drop table if exists iot_cfg_fsu;

drop table if exists iot_cfg_precinct;

drop table if exists iot_cfg_room;

drop table if exists iot_cfg_site;

drop table if exists iot_cfg_sitekind;

drop table if exists iot_cfg_sitelevel;

drop table if exists iot_cfg_storespan;

drop table if exists iot_cfg_telestrategy;

drop table if exists iot_cfg_useralarmfilter;

drop table if exists iot_cfg_usersetting;

drop table if exists iot_cfg_vipmete;

drop table if exists iot_cfg_workstatemask;

drop table if exists iot_his_devicealarm;

drop table if exists iot_his_loginout;

drop table if exists iot_his_operation;

/*==============================================================*/
/* Table: iot_bas_alarmoperation                                */
/*==============================================================*/
create table iot_bas_alarmoperation
(
   alarmoperation_id    tinyint not null,
   name                 varchar(40) not null,
   name_en              varchar(40) not null,
   primary key (alarmoperation_id)
);

alter table iot_bas_alarmoperation comment '告警操作表';

/*==============================================================*/
/* Table: iot_bas_alminfoext                                    */
/*==============================================================*/
create table iot_bas_alminfoext
(
   device_type          smallint not null comment '网管设备类型',
   device_model         smallint not null comment '设备型号编号',
   mete_type            smallint not null comment '监控量类型',
   site_level           tinyint not null comment '站点级别',
   report_level         tinyint comment '告警级别',
   upperlimit1_exist    tinyint not null comment '一级告警上限是否存在',
   upperlimit1          double comment '一级告警上限',
   lowerlimit1_exist    tinyint not null comment '一级告警下限是否存在',
   lowerlimit1          double comment '一级告警下限',
   upperlimit2_exist    tinyint not null comment '二级告警上限是否存在',
   upperlimit2          double comment '二级告警上限',
   lowerlimit2_exist    tinyint not null comment '二级告警下限是否存在',
   lowerlimit2          double comment '二级告警下限',
   upperlimit3_exist    tinyint not null comment '三级告警上限是否存在',
   upperlimit3          double comment '三级告警上限',
   lowerlimit3_exist    tinyint not null comment '三级告警下限是否存在',
   lowerlimit3          double comment '三级告警下限',
   upperlimit4_exist    tinyint not null comment '三级告警下限是否存在',
   upperlimit4          double comment '四级告警上限',
   lowerlimit4_exist    tinyint not null comment '四级告警下限是否存在',
   lowerlimit4          double comment '四级告警下限',
   delayAlarm_time      int default 0 comment '告警延时',
   delayCancelAlarm_time int default 0 comment '告警消除延时',
   delta                double default 0 comment 'Delta值',
   primary key (device_type, device_model, mete_type, site_level)
);

alter table iot_bas_alminfoext comment '四级告警方案表';

/*==============================================================*/
/* Table: iot_bas_changesubject                                 */
/*==============================================================*/
create table iot_bas_changesubject
(
   change_subject       tinyint not null comment '变更主题编号',
   subject_name         varchar(20) comment '变更主题名称',
   subject_name_en      varchar(50) comment '英文名称',
   primary key (change_subject)
);

alter table iot_bas_changesubject comment '配置变更主题';

/*==============================================================*/
/* Table: iot_bas_devicekind                                    */
/*==============================================================*/
create table iot_bas_devicekind
(
   device_kind          tinyint not null comment '所属设备种类',
   kind_name            varchar(40) not null comment '设备种类名称',
   kind_name_en         varchar(40) not null comment '英文名称',
   primary key (device_kind)
);

alter table iot_bas_devicekind comment '设备种类';

/*==============================================================*/
/* Table: iot_bas_devicemodel                                   */
/*==============================================================*/
create table iot_bas_devicemodel
(
   device_model         smallint not null comment '设备型号编号',
   model_name           varchar(40) not null comment '设备型号名称',
   model_name_en        varchar(40) not null comment '英文名称',
   device_type          smallint not null comment '所属设备类型',
   device_kind          tinyint not null comment '所属设备种类',
   manufacturer_id      smallint not null comment '设备厂商编号',
   primary key (device_model)
);

alter table iot_bas_devicemodel comment '系统设备模板表';

/*==============================================================*/
/* Table: iot_bas_devicestatus                                  */
/*==============================================================*/
create table iot_bas_devicestatus
(
   device_status        tinyint not null comment '设备状态类型编号',
   name                 varchar(40) not null comment '设备状态类型名称',
   name_en              varchar(40) not null comment '设备状态类型英文名称',
   primary key (device_status)
);

alter table iot_bas_devicestatus comment '设备状态表';

/*==============================================================*/
/* Table: iot_bas_devicetemplate                                */
/*==============================================================*/
create table iot_bas_devicetemplate
(
   device_model         smallint not null comment '设备型号编号',
   mete_kind            tinyint not null comment '监控量类型',
   bas_mete_id          char(14) not null comment '基本监控量编号',
   bas_channel_no       smallint default 0 comment 'FSU通道号',
   bas_mete_name        varchar(50) comment '监控量名称',
   up_effect            double comment '遥测量上限',
   low_effect           double comment '遥测量下限',
   accuracy             tinyint comment '监控量的有效位数',
   unit                 varchar(8) comment '单位',
   primary key (device_model, bas_mete_id)
);

alter table iot_bas_devicetemplate comment '系统监控量模板表';

/*==============================================================*/
/* Table: iot_bas_devicetype                                    */
/*==============================================================*/
create table iot_bas_devicetype
(
   device_type          smallint not null comment '设备类型编号',
   device_kind          tinyint not null comment '所属设备种类',
   name                 varchar(40) not null comment '设备类型名称',
   name_en              varchar(40) not null comment '设备类型英文名称',
   primary key (device_type)
);

alter table iot_bas_devicetype comment '设备类型表';

/*==============================================================*/
/* Table: iot_bas_devstatusmap                                  */
/*==============================================================*/
create table iot_bas_devstatusmap
(
   device_type          smallint not null comment '设备类型编号',
   device_status        tinyint not null comment '设备状态类型编号'
);

alter table iot_bas_devstatusmap comment '设备状态映射表';

/*==============================================================*/
/* Table: iot_bas_manufacturer                                  */
/*==============================================================*/
create table iot_bas_manufacturer
(
   manufacturer_id      smallint not null comment '厂商编号',
   name                 varchar(80) not null comment '厂商名称',
   name_en              varchar(80) not null comment '厂商名称',
   address              varchar(80) comment '厂商地址',
   address_en           varchar(80) comment '厂商英文地址',
   username             varchar(20) comment '联系负责人',
   telephone            varchar(20) comment '联系负责人',
   mobile               varchar(20) comment '手机号',
   primary key (manufacturer_id)
);

alter table iot_bas_manufacturer comment '制造商信息表';

/*==============================================================*/
/* Table: iot_bas_masktype                                      */
/*==============================================================*/
create table iot_bas_masktype
(
   mask_type            tinyint not null comment '告警屏蔽类型',
   name                 varchar(40) not null comment '告警屏蔽类型名称',
   name_en              varchar(40) not null comment '英文名称',
   primary key (mask_type)
);

alter table iot_bas_masktype comment '告警屏蔽类型表';

/*==============================================================*/
/* Table: iot_bas_metekind                                      */
/*==============================================================*/
create table iot_bas_metekind
(
   mete_kind            tinyint not null comment '变更主题编号',
   kind_name            varchar(20) not null comment '监控量类型名称',
   kind_name_en         varchar(20) not null comment '英文名称',
   primary key (mete_kind)
);

alter table iot_bas_metekind comment '监控量类型表';

/*==============================================================*/
/* Table: iot_bas_nettype                                       */
/*==============================================================*/
create table iot_bas_nettype
(
   net_type             tinyint not null comment '组网类型',
   name                 varchar(40) not null comment '类型名称',
   name_en              varchar(40) not null comment '英文名称',
   primary key (net_type)
);

alter table iot_bas_nettype comment '组网类型表';

/*==============================================================*/
/* Table: iot_bas_reportkind                                    */
/*==============================================================*/
create table iot_bas_reportkind
(
   report_kind          tinyint not null comment '上报种类',
   name                 varchar(40) not null comment '上报种类名称',
   name_en              varchar(40) not null comment '英文名称',
   primary key (report_kind)
);

alter table iot_bas_reportkind comment '遥测量上报种类表';

/*==============================================================*/
/* Table: iot_bas_reportlevel                                   */
/*==============================================================*/
create table iot_bas_reportlevel
(
   report_level         tinyint not null comment '遥信量上报级别',
   level_name           varchar(40) not null comment '上报级别名称',
   level_name_en        varchar(40) comment '上报级别英文名称',
   level_color          int not null default 0 comment '文本颜色;缺省为0（黑色）',
   primary key (report_level)
);

alter table iot_bas_reportlevel comment '遥信量上报级别表';

/*==============================================================*/
/* Table: iot_bas_reporttype                                    */
/*==============================================================*/
create table iot_bas_reporttype
(
   report_type          tinyint not null comment '上报类型',
   name                 varchar(20) not null comment '上报类型名称',
   name_en              varchar(20) comment '英文名称',
   primary key (report_type)
);

alter table iot_bas_reporttype comment '遥信量上报类型表';

/*==============================================================*/
/* Table: iot_bas_resultcode                                    */
/*==============================================================*/
create table iot_bas_resultcode
(
   resultcode_id        smallint not null comment '操作结果编号',
   description          varchar(40) not null comment '操作内容',
   description_en       varchar(40) not null comment '英文说明',
   primary key (resultcode_id)
);

alter table iot_bas_resultcode comment '操作结果表';

/*==============================================================*/
/* Table: iot_bas_runtype                                       */
/*==============================================================*/
create table iot_bas_runtype
(
   run_type             tinyint not null comment '运行状态编号',
   name                 varchar(40) not null comment '名称',
   name_en              varchar(40) not null comment '英文名称'
);

alter table iot_bas_runtype comment '在线运行状态表';

/*==============================================================*/
/* Table: iot_bas_signalkind                                    */
/*==============================================================*/
create table iot_bas_signalkind
(
   report_type          tinyint not null comment '上报类型',
   name                 varchar(40) not null comment '上报类型名称',
   name_en              varchar(40) not null comment '英文名称',
   primary key (report_type)
);

alter table iot_bas_signalkind comment '遥信量类型表';

/*==============================================================*/
/* Table: iot_bas_sitekind                                      */
/*==============================================================*/
create table iot_bas_sitekind
(
   site_kind            tinyint not null comment '站点类型编号',
   name                 varchar(40) not null comment '站点类型名称',
   name_en              varchar(40) not null comment '站点类型英文名称',
   site_level           tinyint not null comment '站点级别',
   primary key (site_kind)
);

alter table iot_bas_sitekind comment '站点类型表';

/*==============================================================*/
/* Table: iot_bas_sitelevel                                     */
/*==============================================================*/
create table iot_bas_sitelevel
(
   site_level           tinyint not null comment '站点级别',
   name                 varchar(40) not null comment '站点级别名称',
   name_en              varchar(40) not null comment '站点级别英文名称',
   primary key (site_level)
);

alter table iot_bas_sitelevel comment '站点级别表';

/*==============================================================*/
/* Table: iot_bas_storespan                                     */
/*==============================================================*/
create table iot_bas_storespan
(
   span_type            tinyint not null comment '存储周期类型编号',
   span                 int not null comment '存储周期',
   primary key (span_type)
);

alter table iot_bas_storespan comment '存储周期表';

/*==============================================================*/
/* Table: iot_bas_teleadjust                                    */
/*==============================================================*/
create table iot_bas_teleadjust
(
   bas_mete_id          varchar(14) not null comment '遥调量型号编号',
   bas_mete_name        varchar(50) not null comment '遥调量名称',
   bas_mete_name_en     varchar(50) not null comment '英文名称',
   device_type          smallint not null comment '设备类型',
   mete_type            smallint not null comment '遥调量类型',
   same_metetype_index  smallint default 0 comment '同类遥调量子序号',
   metetype_name        varchar(50) not null comment '遥测量类型名称',
   metetype_name_en     varchar(50) not null comment '监控量类型的英文名称',
   mete_kind            tinyint default 3 comment '监控量类型',
   mete_level           tinyint default 1 comment '监控量重要程度',
   up_effect            double default 0 comment '有效上限',
   low_effect           double default 0 comment '有效下限',
   default_value        double default 0 comment '缺省值',
   accuracy             tinyint default 1 comment '小数点后有效位数',
   unit                 varchar(8) not null comment '单位',
   bas_channel_no       smallint default 0 comment 'FSU通道号',
   primary key (bas_mete_id, device_type, mete_type)
);

alter table iot_bas_teleadjust comment '遥调量基本表';

/*==============================================================*/
/* Table: iot_bas_telecontrol                                   */
/*==============================================================*/
create table iot_bas_telecontrol
(
   bas_mete_id          varchar(14) not null comment '基本遥控量编号',
   bas_mete_name        varchar(50) not null comment '基本遥控量名称',
   bas_mete_name_en     varchar(50) comment '英文名称',
   device_type          smallint not null comment '设备类型',
   mete_type            smallint not null comment '遥测量类型',
   same_metetype_index  smallint default 0 comment '同类遥控量子序号',
   metetype_name        varchar(50) not null comment '遥测量类型名称',
   metetype_name_en     varchar(50) not null comment '监控量类型的英文名称',
   mete_kind            tinyint default 4 comment '监控量类型',
   bas_channel_no       smallint default 0 comment 'FSU通道号',
   primary key (bas_mete_id, device_type, mete_type)
);

alter table iot_bas_telecontrol comment '遥控量基本表';

/*==============================================================*/
/* Table: iot_bas_telemeter                                     */
/*==============================================================*/
create table iot_bas_telemeter
(
   bas_mete_id          varchar(14) not null comment '基本遥测量序号',
   bas_mete_name        varchar(50) not null comment '基本遥测量名称',
   bas_mete_name_en     varchar(50) comment '基本遥测量的英文名称',
   device_type          smallint not null comment '设备类型',
   mete_type            smallint not null default 0 comment '遥测量类型',
   same_metetype_index  smallint default 0 comment '同类遥测量类型子序号',
   metetype_name        varchar(50) not null comment '遥测量类型名称',
   metetype_name_en     varchar(50) not null comment '监控量类型的英文名称',
   mete_kind            tinyint default 1 comment '监控量类型',
   up_effect            double default 0 comment '有效上限',
   low_effect           double default 0 comment '有效下限',
   accuracy             tinyint default 1 comment '小数点后有效位数',
   unit                 varchar(8) not null comment '单位',
   bas_channel_no       smallint default 0 comment 'FSU通道号',
   primary key (bas_mete_id, device_type, mete_type)
);

alter table iot_bas_telemeter comment '遥测量基本表';

/*==============================================================*/
/* Table: iot_bas_telesignal                                    */
/*==============================================================*/
create table iot_bas_telesignal
(
   bas_mete_id          varchar(14) not null comment '基本遥信量编号',
   bas_mete_name        varchar(50) not null comment '基本遥信量名称',
   bas_mete_name_en     varchar(50) comment '英文名称',
   device_type          smallint not null comment '设备类型',
   mete_type            smallint not null comment '遥信量类型',
   same_metetype_index  smallint default 0 comment '同类遥信量类型子序号',
   metetype_name        varchar(50) not null comment '遥测量类型名称',
   metetype_name_en     varchar(50) not null comment '监控量类型的英文名称',
   mete_kind            tinyint default 2 comment '监控量类型',
   up_effect            tinyint unsigned null default 255 comment '有效上限',
   signal_kind          tinyint not null comment '遥信量种类',
   report_type          tinyint default 0 comment '上报模式',
   report_level         tinyint default 0 comment '上报级别',
   bas_channel_no       smallint default 0 comment 'FSU通道号',
   primary key (bas_mete_id, device_type, mete_type)
);

alter table iot_bas_telesignal comment '遥信量基本表';

/*==============================================================*/
/* Table: iot_bas_telestrategy                                  */
/*==============================================================*/
create table iot_bas_telestrategy
(
   device_type          smallint not null comment '设备类型编号',
   mete_type            smallint not null comment '遥测量类型',
   site_level           tinyint not null comment '站点级别',
   device_status        tinyint not null comment '设备状态类型编号',
   save_on_schedul      tinyint default 0 comment '是否整点存储',
   span_type            tinyint default 3 comment '存储周期类型编号',
   change_limit         double default 0 comment '变化幅度门限',
   save_real_value      tinyint default 0 comment '是否存储24小时实时值',
   save_realvalue_time smallint default 0 comment '实时值存储周期'
);

alter table iot_bas_telestrategy comment '监控量存储策略表';

/*==============================================================*/
/* Table: iot_bas_useroperation                                 */
/*==============================================================*/
create table iot_bas_useroperation
(
   useroperation_id     smallint not null comment '操作编号',
   description          varchar(160) not null comment '操作内容',
   description_en       varchar(160) not null comment '英文说明'
);

alter table iot_bas_useroperation comment '用户操作表';

/*==============================================================*/
/* Table: iot_cfg_alarmmask                                     */
/*==============================================================*/
create table iot_cfg_alarmmask
(
   tenant_id            smallint not null comment '设备类型编号',
   site_id              smallint not null comment '设备状态类型编号',
   device_id            int not null,
   mete_id              int not null,
   mask_type            tinyint default 0,
   sunday               char(13) not null,
   monday               char(13) not null,
   tuesday              char(13) not null,
   wednesday            char(13) not null,
   thursday             char(13) not null,
   friday               char(13) not null,
   saturday             char(13) not null,
   primary key (tenant_id, device_id, mete_id)
);

alter table iot_cfg_alarmmask comment '告警时段屏蔽表';

/*==============================================================*/
/* Table: iot_cfg_alminfoext                                    */
/*==============================================================*/
create table iot_cfg_alminfoext
(
   device_type          smallint not null comment '网管设备类型',
   device_model         smallint not null comment '设备型号编号',
   mete_type            smallint not null comment '监控量类型',
   site_level           tinyint not null comment '站点级别',
   report_level         tinyint comment '告警级别',
   upperlimit1_exist    tinyint not null comment '一级告警上限是否存在',
   upperlimit1          double comment '一级告警上限',
   lowerlimit1_exist    tinyint not null comment '一级告警下限是否存在',
   lowerlimit1          double comment '一级告警下限',
   upperlimit2_exist    tinyint not null comment '二级告警上限是否存在',
   upperlimit2          double comment '二级告警上限',
   lowerlimit2_exist    tinyint not null comment '二级告警下限是否存在',
   lowerlimit2          double comment '二级告警下限',
   upperlimit3_exist    tinyint not null comment '三级告警上限是否存在',
   upperlimit3          double comment '三级告警上限',
   lowerlimit3_exist    tinyint not null comment '三级告警下限是否存在',
   lowerlimit3          double comment '三级告警下限',
   upperlimit4_exist    tinyint not null comment '三级告警下限是否存在',
   upperlimit4          double comment '四级告警上限',
   lowerlimit4_exist    tinyint not null comment '四级告警下限是否存在',
   lowerlimit4          double comment '四级告警下限',
   delayAlarm_time      int default 0 comment '告警延时',
   delayCancelAlarm_time int default 0 comment '告警消除延时',
   delta                double default 0 comment 'Delta值',
   tenant_id            Int(11) not null comment '租户编号',
   primary key (device_type, device_model, mete_type, site_level)
);

alter table iot_cfg_alminfoext comment '四级告警方案表';

/*==============================================================*/
/* Table: iot_cfg_building                                      */
/*==============================================================*/
create table iot_cfg_building
(
   tenant_id            Int(11) not null comment '租户编号',
   precinct_id          tinyint not null comment '管辖区编号',
   building_id       tinyint not null comment '楼栋编号',
   building_name        varchar(40) comment '楼栋名称',
   primary key (tenant_id, precinct_id, building_id)
);

alter table iot_cfg_building comment '楼栋信息表';

/*==============================================================*/
/* Table: iot_cfg_device                                        */
/*==============================================================*/
create table iot_cfg_device
(
   tenant_id            Int(11) not null comment '租户编号',
   device_id            int not null comment '设备编号',
   device_name          varchar(40) not null comment '设备名称',
   site_id              smallint not null comment 'TENANT下的站点编号',
   device_index         tinyint not null comment '站点内的设备编号',
   device_model         int not null comment '设备型号',
   device_type          smallint not null comment '设备类型',
   device_kind          tinyint not null comment '设备种类',
   user_device_id       varchar(40) default 'none' comment '用户设备编号',
   fsu_id               int comment 'FSU设备编号',
   launchtime           datetime not null comment '投入使用的时间',
   device_manufacturertype smallint default 0 comment '厂商设备型号',
   manufacturer_id      smallint default 0 comment '厂商编号',
   primary key (tenant_id, device_id)
);

alter table iot_cfg_device comment '设备配置表';

/*==============================================================*/
/* Table: iot_cfg_devicestatus                                  */
/*==============================================================*/
create table iot_cfg_devicestatus
(
   device_status        tinyint not null comment '设备状态类型编号',
   name                 varchar(40) not null comment '设备状态类型名称',
   name_en              varchar(40) not null comment '设备状态类型英文名称',
   primary key (device_status)
);

alter table iot_cfg_devicestatus comment '设备状态表';

/*==============================================================*/
/* Table: iot_cfg_devstatusmap                                  */
/*==============================================================*/
create table iot_cfg_devstatusmap
(
   device_type          smallint not null comment '设备类型编号',
   device_status        tinyint not null comment '设备状态类型编号'
);

alter table iot_cfg_devstatusmap comment '设备状态映射表';

/*==============================================================*/
/* Table: iot_cfg_express                                       */
/*==============================================================*/
create table iot_cfg_express
(
   express_id           int not null comment '操作编号',
   express_name         varchar(50) not null comment '操作名称',
   express_value        varchar(512) not null comment '表达式及公式内容',
   device_type          smallint not null comment '设备类型',
   channel_no           int comment '通道号',
   mete_type            tinyint comment '监控量类型',
   control_type         tinyint comment '控制类型',
   meteid_set           varchar(1000) comment '监控量编码集合',
   express_type         tinyint not null comment '类型',
   is_system            tinyint not null comment '是否系统表达式',
   primary key (express_id)
);

alter table iot_cfg_express comment '表达式及计算公式固化表';

/*==============================================================*/
/* Table: iot_cfg_floor                                         */
/*==============================================================*/
create table iot_cfg_floor
(
   precinct_id          tinyint not null comment '楼栋编号',
   floor_id          tinyint not null comment '楼层编号',
   floor_name           varchar(40) not null comment '栋层室名称',
   primary key (precinct_id, floor_id)
);

alter table iot_cfg_floor comment '楼层信息表';

/*==============================================================*/
/* Table: iot_cfg_fsu                                           */
/*==============================================================*/
create table iot_cfg_fsu
(
   tenant_id            Int(11) not null comment '租户编号',
   fsu_id               int not null comment 'FSU设备编号',
   site_id              smallint not null comment '站点编号',
   net_type             tinyint comment '组网类型',
   net_info             varchar(255) comment '网元信息',
   port_num             tinyint comment '接口数',
   device_manager       varchar(20) comment '设备负责人',
   maintain_mobile      varchar(20) comment '维护人电话',
   primary key (tenant_id, fsu_id)
);

alter table iot_cfg_fsu comment 'FSU设备信息表';

/*==============================================================*/
/* Table: iot_cfg_precinct                                      */
/*==============================================================*/
create table iot_cfg_precinct
(
   tenant_id            Int(11) not null comment '租户编号',
   precinct_id          tinyint not null comment '管辖区编号',
   precinct_name        varchar(40) not null comment '管辖区名称',
   up_precinct_id    tinyint default 0 comment '上级管理区编号',
   primary key (tenant_id, precinct_id)
);

alter table iot_cfg_precinct comment '管辖区配置信息';

/*==============================================================*/
/* Table: iot_cfg_room                                          */
/*==============================================================*/
create table iot_cfg_room
(
   floor_id          tinyint not null comment '楼层编号',
   room_id              tinyint not null comment '楼室编号',
   room_name            varchar(40) not null comment '栋层室名称',
   primary key (floor_id, room_id)
);

alter table iot_cfg_room comment '楼室信息表';

/*==============================================================*/
/* Table: iot_cfg_site                                          */
/*==============================================================*/
create table iot_cfg_site
(
   tenant_id            Int(11) not null comment '租户编号',
   site_id              smallint not null comment '同一tenant下的站点号',
   site_name            varchar(40) not null comment '站点名称',
   site_kind            tinyint not null comment '站点类型',
   precinct_id          tinyint default 0 comment '管辖区编号',
   room_id              tinyint default 0 comment '楼室编号',
   floor_id          tinyint default 0 comment '楼层编号',
   building_id       tinyint default 0 comment '楼栋编号',
   user_site_id         varchar(40) comment '用户站点编号',
   is_templet           tinyint default 0 comment '是否为站点模板',
   net_type             tinyint default 0 comment '组网方式',
   run_type             tinyint default 0 comment '在线运行状态',
   primary key (tenant_id, site_id)
);

alter table iot_cfg_site comment '站点配置信息';

/*==============================================================*/
/* Table: iot_cfg_sitekind                                      */
/*==============================================================*/
create table iot_cfg_sitekind
(
   site_kind            tinyint not null comment '站点类型编号',
   name                 varchar(40) not null comment '站点类型名称',
   name_en              varchar(40) not null comment '站点类型英文名称',
   site_level           tinyint not null comment '站点级别',
   primary key (site_kind)
);

alter table iot_cfg_sitekind comment '站点类型表';

/*==============================================================*/
/* Table: iot_cfg_sitelevel                                     */
/*==============================================================*/
create table iot_cfg_sitelevel
(
   site_level           tinyint not null comment '站点级别',
   name                 varchar(40) not null comment '站点级别名称',
   name_en              varchar(40) not null comment '站点级别英文名称',
   primary key (site_level)
);

alter table iot_cfg_sitelevel comment '站点级别表';

/*==============================================================*/
/* Table: iot_cfg_storespan                                     */
/*==============================================================*/
create table iot_cfg_storespan
(
   span_type            tinyint not null comment '存储周期类型编号',
   span                 int not null comment '存储周期',
   primary key (span_type)
);

alter table iot_cfg_storespan comment '存储周期表';

/*==============================================================*/
/* Table: iot_cfg_telestrategy                                  */
/*==============================================================*/
create table iot_cfg_telestrategy
(
   device_type          smallint not null comment '设备类型编号',
   mete_type            smallint not null comment '遥测量类型',
   site_level           tinyint not null comment '站点级别',
   device_status        tinyint not null comment '设备状态类型编号',
   save_on_schedul      tinyint default 0 comment '是否整点存储',
   span_type            tinyint default 3 comment '存储周期类型编号',
   change_limit         double default 0 comment '变化幅度门限',
   save_real_value      tinyint default 0 comment '是否存储24小时实时值',
   save_realvalue_time smallint default 0 comment '实时值存储周期'
);

alter table iot_cfg_telestrategy comment '监控量存储策略表';

/*==============================================================*/
/* Table: iot_cfg_useralarmfilter                               */
/*==============================================================*/
create table iot_cfg_useralarmfilter
(
   tenant_id            Int(11) not null comment '租户编号',
   role_id              tinyint not null comment '角色id',
   type                 tinyint default 0 comment '过滤条件类型',
   name                 varchar(40) not null comment '过滤条件名称',
   value                text not null comment '过滤条件值',
   primary key (tenant_id, role_id, name)
);

alter table iot_cfg_useralarmfilter comment '用户告警过滤条件表';

/*==============================================================*/
/* Table: iot_cfg_usersetting                                   */
/*==============================================================*/
create table iot_cfg_usersetting
(
   tenant_id            int(11) not null,
   user_id              int(11) not null comment '登录用户id',
   type                 tinyint not null comment '设置类型',
   value                tinyint not null comment 'value',
   primary key (tenant_id, user_id, type)
);

alter table iot_cfg_usersetting comment '用户个性化设置表';

/*==============================================================*/
/* Table: iot_cfg_vipmete                                       */
/*==============================================================*/
create table iot_cfg_vipmete
(
   device_type          smallint not null comment '设备类型',
   mete_type            smallint not null comment '监控量类型',
   site_level           tinyint not null comment '站点级别',
   mete_kind            tinyint not null comment '监控量种类',
   primary key (device_type, mete_type, site_level)
);

alter table iot_cfg_vipmete comment '重要监控量表';

/*==============================================================*/
/* Table: iot_cfg_workstatemask                                 */
/*==============================================================*/
create table iot_cfg_workstatemask
(
   tenant_id            int(11) not null comment '租户编号',
   site_id              smallint not null comment '站点编号',
   device_id            int not null comment '设备编号',
   set_option           tinyint not null comment '设置站点（设备）的工程状态',
   state                tinyint not null comment '设置的类型',
   user_id              varchar(20) not null comment '用户名',
   start_time           datetime not null comment '屏蔽启用时间',
   end_time             datetime not null comment '屏蔽停止时间',
   operate_content      varchar(255) comment '操作内容',
   primary key (tenant_id, site_id, device_id)
);

alter table iot_cfg_workstatemask comment '工程屏蔽设置表';

/*==============================================================*/
/* Table: iot_his_devicealarm                                   */
/*==============================================================*/
create table iot_his_devicealarm
(
   tenant_id            int(11) not null comment '租户编号',
   alarm_no             int not null comment '告警编号',
   site_id              smallint not null comment '站点编号',
   device_id            int not null comment '设备编号',
   mete_id              int not null comment '监控量编号',
   signal_kind          tinyint not null comment '告警量类型',
   report_level         tinyint not null comment '告警级别',
   device_type          smallint not null comment '设备类型',
   mete_type            smallint not null comment '遥信量类型',
   alarm_time           datetime not null comment '告警产生时间',
   alarm_value          real not null comment '告警值',
   alarm_state          tinyint not null comment '告警状态',
   clear_time           datetime comment '告警消除时间',
   clear_value          double default 0.0 comment '消除值',
   is_clear             tinyint default 0 comment '是否已消除',
   alarm_span           int default 0 comment '告警历时',
   mask_type            tinyint default 0 comment '屏蔽方式',
   timestamp            timestamp comment '记录更新时间戳',
   alarm_limit          double comment '告警阀值'
);

alter table iot_his_devicealarm comment '设备告警表';

/*==============================================================*/
/* Table: iot_his_loginout                                      */
/*==============================================================*/
create table iot_his_loginout
(
   tenant_id            int(11) not null comment '租户编号',
   user_id              int(11) not null comment '系统用户ID',
   logintime            datetime comment '登录时间',
   logoutime            datetime comment '退出时间'
);

alter table iot_his_loginout comment '用户登退录表';

/*==============================================================*/
/* Table: iot_his_operation                                     */
/*==============================================================*/
create table iot_his_operation
(
   tenant_id            int(11) not null comment '用户所在的租户编号',
   operate_time         datetime not null comment '操作时间',
   user_id              int(11) not null comment '系统用户id',
   site_id              smallint not null comment '操作针对的站点编号',
   device_id            int not null comment '操作针对的设备编号',
   mete_id              int not null comment '操作针对的监控量类型',
   mete_kind            tinyint not null comment '监控量类型',
   operate_code         tinyint not null comment '操作内容编号',
   result_code          tinyint default 0 comment '操作结果',
   operate_content      varchar(255) not null comment '操作内容'
);

alter table iot_his_operation comment '用户操作日志表';
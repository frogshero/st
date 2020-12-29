drop table if exists prototype_info_123 ;
CREATE TABLE prototype_info_123 (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    group_name VARCHAR(50) comment '组名',
    field_title VARCHAR(50) comment 'title',
    field_order VARCHAR(10) comment '显示顺序',
    field_style VARCHAR(100) comment '输入方式',
    editable VARCHAR(50) comment '是否可以编辑',
    default_val VARCHAR(50) comment '默认值',
    description VARCHAR(500) comment '描述',
    data_source VARCHAR(50) comment '数据源',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1405 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='原型信息';

insert into prototype_info_123(prototype_name, group_name, field_title, field_order, field_style, editable, default_val, description, data_source)
values('userList','个人信息','员工编号','1','','','','默认按设置自动生成(5位纯数字)，可手工修改',''),
('userList','个人信息','姓名','2','','','','',''),
('userList','个人信息','性别','3','下拉','','','',''),
('userList','个人信息','出生日期','8','yyyy-mm-dd','','','',''),
('userList','个人信息','婚否','9','下拉','','','',''),
('userList','个人信息','学历','10','输入、选择','','','','学历'),
('userList','个人信息','毕业学校','11','','','','',''),
('userList','个人信息','身份证号','12','','','','',''),
('userList','个人信息','电话','13','','','','',''),
('userList','个人信息','邮箱','14','','','','',''),
('userList','个人信息','地址','','','','','',''),
('userList','单位信息','员工卡号','12','','','','',''),
('userList','单位信息','头像','','图片上传','','','',''),
('userList','单位信息','部门','4','输入、选择','','','','部门'),
('userList','单位信息','岗位','5','输入、选择','','','','岗位'),
('userList','单位信息','班组','6','输入、选择','','','','班组'),
('userList','单位信息','员工类别','7','下拉','','','','员工类别'),
('userList','单位信息','费率','15','','','','',''),
('userList','单位信息','入职日期','16','yyyy-mm-dd','','','',''),
('userList','单位信息','离职日期','17','yyyy-mm-dd','','','',''),
('userList','单位信息','备注','18','','','','',''),
('userList','单位信息','停用','','复选框','','0','',''),
('userList','单位信息','维护人','','','y','','',''),
('userList','单位信息','维护时间','','yyyy-mm-dd hh:mm','y','','','')
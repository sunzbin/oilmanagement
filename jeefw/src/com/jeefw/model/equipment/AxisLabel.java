package com.jeefw.model.equipment;

import com.jeefw.model.equipment.param.AxisLabelParameter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Title: 轴位标签
 * @Description:
 * @Date:2018-05-04 上午 10:19
 * @author:liangyingnan
 */
public class AxisLabel extends AxisLabelParameter{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	/**
	 * 标签类型
	 */
	@Column(name = "label_type")
	private String  labelType ;

	/**
	 *车型代码
	 */
	@Column(name = "car_code")
	private String  carCode ;

	/**
	 *车号
	 */
	@Column(name = "car_code")
	private String  carNumber ;

	/**
	 *轴位
	 * 一轴：1；二轴：2；三轴：3；四轴：4；五轴：5；六轴：6
	 */
	@Column(name="axial_position")
	private String  AxialPosition;

	/**
	 *点位
	 * 牵引电机传动端:1;抱轴体:2;牵引电机非传动端:3
	 */
	@Column(name="point_position")
	private String  pointPosition;

	/**
	 *油脂类型代码
	 */
	@Column(name="grease_type_code")
	private String GreaseTypeCode;

	/**
	 * 创建人id
	 */
	@Column(name="create_id")
	private String createId;

	/**
	 * 所属部门
	 */
	@Column(name="dep_id")
	private String depId;

	/**
	 * 标签详情id
	 */
	@Column(name="labelDetail_id")
	private Long labelDetailId;










}

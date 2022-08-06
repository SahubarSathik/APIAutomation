package com.payloads;

import com.base.BaseClass;
import com.pojo.AddAddress_Input_Pojo;
import com.pojo.DeleteAddress_Input_pojo;
import com.pojo.UpdateAddress_Input_pojo;

/**
 * @Description: Used to Pass the Payloads
 * @Date 17/07/2022
 * @author Sathik
 *
 */
public class Payload extends BaseClass {
	/**
	 * @Description: Used to Pass the Add Addres Details
	 * @Date 17/06/2022
	 * @author Sathik
	 * @param first_name
	 * @param last_name
	 * @param mobile
	 * @param apartment
	 * @param state
	 * @param city
	 * @param country
	 * @param zipcode
	 * @param address
	 * @param address_type
	 * @return AddAddress_Input_Pojo Class
	 */
	public static AddAddress_Input_Pojo creteAddress(String first_name, String last_name, String mobile,
			String apartment, int state, int city, int country, String zipcode, String address, String address_type) {

		AddAddress_Input_Pojo addAddress_Input_Pojo = new AddAddress_Input_Pojo(first_name, last_name, mobile,
				apartment, state, city, country, zipcode, address, address_type);
		return addAddress_Input_Pojo;
	}

	/**
	 * @Description: Used to Pass the Update Addres Details
	 * @Date 17/06/2022
	 * @author Sathik
	 * @param address_id
	 * @param first_name
	 * @param last_name
	 * @param mobile
	 * @param apartment
	 * @param state
	 * @param city
	 * @param country
	 * @param zipcode
	 * @param address
	 * @param address_type
	 * @return UpdateAddress_Input_pojo Class
	 */
	public static UpdateAddress_Input_pojo updateAddress(int address_id, String first_name, String last_name,
			String mobile, String apartment, int state, int city, int country, String zipcode, String address,
			String address_type) {

		UpdateAddress_Input_pojo updateAddress_Input_pojo = new UpdateAddress_Input_pojo(String.valueOf(address_id),
				first_name, last_name, mobile, apartment, state, city, country, zipcode, address, address_type);
		return updateAddress_Input_pojo;

	}

	/**
	 * @Description User Delete Address
	 * @Date 17/06/2022
	 * @author Sathik
	 * @param address_id
	 * @return DeleteAddress_Input_pojo
	 */
	public static DeleteAddress_Input_pojo deleteAddress(int address_id) {
		DeleteAddress_Input_pojo deleteAddress_Input_pojo = new DeleteAddress_Input_pojo(String.valueOf(address_id));
		return deleteAddress_Input_pojo;
	}
}

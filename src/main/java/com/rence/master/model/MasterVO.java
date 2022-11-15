/**
 * @author 전판근
 */

package com.rence.master.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterVO implements Serializable {

	private String master_no;
	private String master_id;
	private String master_pw;
}

/**
 * Copyright 1996-2014 FoxBPM Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author kenshin
 */
package org.foxbpm.engine.impl.cmd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.foxbpm.engine.impl.identity.UserDefinition;
import org.foxbpm.engine.impl.identity.UserTo;
import org.foxbpm.engine.impl.interceptor.Command;
import org.foxbpm.engine.impl.interceptor.CommandContext;
import org.foxbpm.engine.impl.util.StringUtil;

public class GetAgentUsersAndCountCmd implements  Command<List<Map<String, Object>>>{

	protected String userId;
	
	public GetAgentUsersAndCountCmd(String userId){
		
		this.userId=userId;
		
	}
	
	public List<Map<String, Object>> execute(CommandContext commandContext) {
		
		List<Map<String, Object>> returnListMaps=new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> listmMaps=commandContext.getTaskManager().findAgentUsers(userId);
		UserDefinition userDefinition=commandContext.getProcessEngineConfigurationImpl().getUserDefinition();
		
		for (Map<String, Object> map : listmMaps) {
			String userIdDataString=StringUtil.getString(map.get("EID"));
			
			if(userIdDataString!=null&&!userIdDataString.equals("")){
				UserTo userTo=userDefinition.findUserByUserId(userIdDataString);
				if(userTo!=null){
					Map<String, Object> mapDataMap=new HashMap<String, Object>();
					mapDataMap.put("userid", userTo.getUserId());
					mapDataMap.put("username", userTo.getUserName());
					mapDataMap.put("count", 0);
					returnListMaps.add(mapDataMap);
				}
			}
		}
		return returnListMaps;
	}
	
	

}

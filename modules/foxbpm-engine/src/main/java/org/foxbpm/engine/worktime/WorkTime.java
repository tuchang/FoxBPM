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
package org.foxbpm.engine.worktime;

import java.util.Date;

import org.foxbpm.engine.runtime.ExecutionContext;

public interface WorkTime {
	
	/**
	 * 判断时间是不是工作时间
	 * @param nowDate 时间
	 * @return
	 */
	public boolean isWorkDate(Date nowDate);
	
	public Date  getWorkTime (Date newDate,int days,int hours,int minutes, int seconds,ExecutionContext executionContext);

}

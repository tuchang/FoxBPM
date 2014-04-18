/**
 * Copyright 1996-2014 FoxBPM ORG.
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
 * @author ych
 */
package org.foxbpm.engine.impl.interceptor;

import org.foxbpm.engine.impl.Context;
import org.foxbpm.engine.impl.ProcessEngineConfigurationImpl;

/**
 * @author kenshin
 */
public class CommandContextInterceptor extends CommandInterceptor {

  protected CommandContextFactory commandContextFactory;
  protected ProcessEngineConfigurationImpl processEngineConfiguration;

  public CommandContextInterceptor() {
	  
  }

  public CommandContextInterceptor(CommandContextFactory commandContextFactory, ProcessEngineConfigurationImpl processEngineConfiguration) {
    this.commandContextFactory = commandContextFactory;
    this.processEngineConfiguration = processEngineConfiguration;
  }

  public <T> T execute(Command<T> command) {
	    CommandContext context = Context.getCommandContext();
	    
	    if (context == null) { 
	    	context = commandContextFactory.createCommandContext(command);    	
	    }  
	    try {
	      // Push on stack
	      Context.setCommandContext(context);
	      Context.setProcessEngineConfiguration(processEngineConfiguration);
	      return next.execute(command);
	      
	    }finally {
	    	try{
	    		context.close();
	    	}finally{
	    		 Context.removeCommandContext();
	       	     Context.removeProcessEngineConfiguration();
	    	}
	    }
  }
  
  public CommandContextFactory getCommandContextFactory() {
    return commandContextFactory;
  }
  
  public void setCommandContextFactory(CommandContextFactory commandContextFactory) {
    this.commandContextFactory = commandContextFactory;
  }

  public ProcessEngineConfigurationImpl getProcessEngineConfiguration() {
    return processEngineConfiguration;
  }

  public void setProcessEngineContext(ProcessEngineConfigurationImpl processEngineContext) {
    this.processEngineConfiguration = processEngineContext;
  }
}

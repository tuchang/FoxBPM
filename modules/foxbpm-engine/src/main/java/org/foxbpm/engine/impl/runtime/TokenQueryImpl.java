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
 * @author ych
 */
package org.foxbpm.engine.impl.runtime;

import java.util.List;

import org.foxbpm.engine.impl.interceptor.CommandContext;
import org.foxbpm.engine.impl.interceptor.CommandExecutor;
import org.foxbpm.engine.impl.query.AbstractQuery;
import org.foxbpm.engine.runtime.Token;
import org.foxbpm.engine.runtime.TokenQuery;

public class TokenQueryImpl extends AbstractQuery<TokenQuery, Token> implements TokenQuery {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 流程实例ID
	protected String processInstanceId;
	// 令牌ID
	protected String tokenId;
	// 是否结束
	protected boolean end;

	public TokenQueryImpl() {

	}

	public TokenQueryImpl(CommandContext commandContext) {
		super(commandContext);
	}

	public TokenQueryImpl(CommandExecutor commandExecutor) {
		super(commandExecutor);
	}

	public TokenQuery processInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
		return this;
	}

	public TokenQuery tokenId(String tokenId) {
		this.tokenId = tokenId;
		return this;
	}

	public TokenQuery tokenIsEnd() {
		this.end = true;
		return this;
	}

	public TokenQuery tokenNotEnd() {
		this.end = false;
		return this;
	}

	public long executeCount(CommandContext commandContext) {
		checkQueryOk();
		return commandContext.getTokenManager().findTokenCountByQueryCriteria(this);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Token> executeList(CommandContext commandContext) {
		checkQueryOk();
		return (List) commandContext.getTokenManager().findTokenByQueryCriteria(this);
	}

	public TokenQuery orderByProcessInstanceId() {
		return orderBy(TokenQueryProperty.PROCESSINSTANCE_ID);
	}

	public TokenQuery orderByTokenId() {
		return orderBy(TokenQueryProperty.TOKEN_ID);
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public String getTokenId() {
		return tokenId;
	}

	public boolean getEnd() {
		return end;
	}
	
	@Override
	public String getOrderBy() {
		// TODO Auto-generated method stub
		return "RES.ID";
	}
}

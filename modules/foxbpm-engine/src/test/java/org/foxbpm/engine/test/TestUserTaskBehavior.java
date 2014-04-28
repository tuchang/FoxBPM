package org.foxbpm.engine.test;
import java.io.InputStream;

import org.foxbpm.engine.impl.bpmn.parser.BpmnParseHandlerImpl;
import org.foxbpm.engine.modelparse.ProcessModelParseHandler;
import org.foxbpm.kernel.process.KernelProcessDefinition;
import org.foxbpm.kernel.runtime.KernelProcessInstance;
import org.foxbpm.kernel.test.KernelTestCase;



public class TestUserTaskBehavior extends KernelTestCase{

public void testProcessDefinitionBuilder(){
	
	ProcessModelParseHandler parse = new BpmnParseHandlerImpl();
	InputStream is = this.getClass().getClassLoader().getResourceAsStream("process_2222.bpmn");
	KernelProcessDefinition processDefinition = parse.createProcessDefinition("process_2222",is);
	KernelProcessInstance processInstance=processDefinition.createProcessInstance();
	processInstance.start();
	
//	assertTrue(processInstance.getRootToken().isEnded());
//	assertTrue(processInstance.isEnded());
	
	
		
//		KernelProcessDefinition processDefinition=new ProcessDefinitionBuilder("kernelTest")
//		.createFlowNode("start")
//        .initial()
//        .behavior(new AutomaticBehavior())
//        .sequenceFlow("task")
//        .endFlowNode()
//        .createFlowNode("task")
//        .behavior(new AutomaticBehavior())
//        .sequenceFlow("end")
//        .endFlowNode()
//        .createFlowNode("end")
//        .behavior(new EndBehavior())
//        .endFlowNode()
//		.buildProcessDefinition();

	
		
		
	}
}

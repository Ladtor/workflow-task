### 说明
[工作流](../../../workflow)项目任务节点基础包

### 使用
通过继承`com.ladtor.workflow.task.task.AbstractTask`抽象类实现具体任务逻辑，详见[Workflow-Test](../../../workflow-test)

### 扩展
默认使用Http通信

可以通过实现`com.ladtor.workflow.task.sender.Sender`以使用消息等通信方式
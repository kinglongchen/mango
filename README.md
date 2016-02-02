# mango

com.kinglong.mango.node包中包含了如下定义的节点:
-----------mango中的节点-----------------------zookeeper对应的节点--------------

RootNode 根节点;<===============================>/

ConfigNode 配置对应节点;<========================>/config/

MangoNode mango对应的节点;<======================>/config/mango/

AppNode 应用程序对应的节点;<======================>/config/mango/{app.name}/

ClassNode 类对应的节点;<=========================>/config/mango/{app.name}/{class.name}/

FieldNode 类中字段对应的节点;<====================>/config/mango/{app.name}{class.name}/{filed.name}/

-----------------------------------------------------------------------------------


从mango中的节点和zookeeper对应的节点之间的关系,我们可以看出各个节点对应的层次关系:
RootNode
    |
    |
ConfigNode
    |
    |
MangoNode
    |
    |
AppNode
    |
    |
ClassNode
    |
    |
FiledNode





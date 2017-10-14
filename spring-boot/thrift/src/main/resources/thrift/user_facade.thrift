namespace java com.lyd.spring.thrift.gen.facade

include "user.thrift"

/**
*   用户相关服务
**/
service UserFacade{

    /**
    *   用户注册
    **/
    string reg(1:user.User user),

    /**
    *   用户查询
    **/
    list<user.User> find(1:string username),

}

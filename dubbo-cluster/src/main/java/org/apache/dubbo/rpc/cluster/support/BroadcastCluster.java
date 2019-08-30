/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.rpc.cluster.support;

import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.Cluster;
import org.apache.dubbo.rpc.cluster.Directory;

/**
 * BroadcastCluster
 *
 * qfz> 广播调用所有提供者，逐个调用，任意一台报错则报错。通常用于通知所有提供者更新缓存或日志等本地资源信息。
 */
public class BroadcastCluster implements Cluster {

    @Override
    public <T> Invoker<T> join(Directory<T> directory) throws RpcException {
        //qfz>  这个的逻辑也是很简单,遍历所有Invokers, 逐个调用每个invoker,有异常就catch,以免影响到剩下的调用,那这个和AvailableCluster有什么区别?
        // 当然有啊,仔细看就知道,BroadcastCluster是要遍历调用完全部的invoker,而AvailableCluster是只要有一个调用就return了.
        return new BroadcastClusterInvoker<T>(directory);
    }

}

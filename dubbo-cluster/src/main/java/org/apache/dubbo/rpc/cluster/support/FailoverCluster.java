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
 * {@link FailoverClusterInvoker}
 *qfz>  是dubbo里面容错方案的缺省值
 *      失败自动切换，当出现失败，重试其它服务器。
 *      通常用于读操作，但重试会带来更长延迟。可通过 retries="2" 来设置重试次数(不含第一次).
 *
 *      面试会问,dubbo中"读接口"和"写接口"有什么区别?
 *      答案也是很明显的,因为默认FailoverCluster会重试。
 *      如果是"写"类型的接口,如果在网络抖动情况下写多次,所以"写"类型的接口要换成FailfastCluster
 */
public class FailoverCluster implements Cluster {

    public final static String NAME = "failover";

    @Override
    public <T> Invoker<T> join(Directory<T> directory) throws RpcException {
        return new FailoverClusterInvoker<T>(directory);
    }

}

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
 * {@link FailfastClusterInvoker}
 *
 * qfz>  快速失败，只发起一次调用，失败立即报错。通常用于非幂等性的写操作，比如新增记录。
 */
public class FailfastCluster implements Cluster {

    public final static String NAME = "failfast";

    @Override
    public <T> Invoker<T> join(Directory<T> directory) throws RpcException {
        //qfz>  这个的实现应该是实现类里面最简单的,就是调用invoke,调用失败就抛出异常,但是这个却是面试问得最多的
        return new FailfastClusterInvoker<T>(directory);
    }

}

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
 * qfz> 个主要用在分组聚合中,我们来看一下官网的介绍
 *    官网介绍：按组合并返回结果 ，比如菜单服务，接口一样，但有多种实现，用group区分，
 *    现在消费方需从每种group中调用一次返回结果，合并结果返回，这样就可以实现聚合菜单项。
 */
public class MergeableCluster implements Cluster {

    public static final String NAME = "mergeable";

    @Override
    public <T> Invoker<T> join(Directory<T> directory) throws RpcException {
        return new MergeableClusterInvoker<T>(directory);
    }

}

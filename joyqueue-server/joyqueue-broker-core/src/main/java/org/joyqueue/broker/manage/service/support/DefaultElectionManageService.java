/**
 * Copyright 2019 The JoyQueue Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.joyqueue.broker.manage.service.support;

import org.joyqueue.broker.election.ElectionService;
import org.joyqueue.broker.election.LeaderElection;
import org.joyqueue.broker.election.RaftLeaderElection;
import org.joyqueue.broker.manage.service.ElectionManageService;
import org.joyqueue.domain.TopicName;

public class DefaultElectionManageService implements ElectionManageService {
    private ElectionService electionService;

    public DefaultElectionManageService(ElectionService electionService) {
        this.electionService = electionService;
    }

    @Override
    public void restoreElectionMetadata() {
        electionService.syncElectionMetadataFromNameService();
    }

    @Override
    public String describe() {
        return electionService.describe();
    }

    @Override
    public String describeTopic(String topic, int partitionGroup) {
        return electionService.describe(topic, partitionGroup);
    }

    @Override
    public void updateTerm(String topic, int partitionGroup, int term) {
        electionService.updateTerm(topic, partitionGroup, term);
    }

    @Override
    public boolean addReplicaTask(String topic, int partitionGroup, int replicaId) {
        LeaderElection leaderElection = electionService.getLeaderElection(TopicName.parse(topic), partitionGroup);
        if (leaderElection == null || !(leaderElection instanceof RaftLeaderElection)) {
            return false;
        }
        return leaderElection.getReplicaGroup().addReplicaTask(replicaId);
    }
}

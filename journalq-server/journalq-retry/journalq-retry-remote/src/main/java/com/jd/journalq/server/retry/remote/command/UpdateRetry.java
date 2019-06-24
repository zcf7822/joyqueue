/**
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
package com.jd.journalq.server.retry.remote.command;

import com.jd.journalq.network.command.CommandType;
import com.jd.journalq.network.transport.command.JournalqPayload;

import java.util.Arrays;

/**
 * 更新重试
 */
public class UpdateRetry extends JournalqPayload {
    public static byte SUCCESS = 0;
    public static byte EXPIRED = -2;
    public static byte FAILED = 1;
    private String topic;
    private String app;
    private Long[] messages;
    private byte updateType;

    @Override
    public int type() {
        return CommandType.UPDATE_RETRY;
    }

    public UpdateRetry app(final String app) {
        setApp(app);
        return this;
    }

    public UpdateRetry updateType(final byte updateType) {
        setUpdateType(updateType);
        return this;
    }

    public UpdateRetry messages(final Long[] messages) {
        setMessages(messages);
        return this;
    }

    public UpdateRetry topic(final String topic) {
        this.topic = topic;
        return this;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getApp() {
        return this.app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public Long[] getMessages() {
        return this.messages;
    }

    public void setMessages(Long[] messages) {
        this.messages = messages;
    }

    public int getUpdateType() {
        return this.updateType;
    }

    public void setUpdateType(byte updateType) {
        this.updateType = updateType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UpdateRetry{");
        sb.append("topic='").append(topic).append('\'');
        sb.append(", app='").append(app).append('\'');
        sb.append(", updateType=").append(updateType);
        sb.append(", messages=").append(Arrays.toString(messages));
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        UpdateRetry that = (UpdateRetry) o;

        if (updateType != that.updateType) {
            return false;
        }
        if (app != null ? !app.equals(that.app) : that.app != null) {
            return false;
        }
        if (!Arrays.equals(messages, that.messages)) {
            return false;
        }
        if (topic != null ? !topic.equals(that.topic) : that.topic != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (app != null ? app.hashCode() : 0);
        result = 31 * result + (messages != null ? Arrays.hashCode(messages) : 0);
        result = 31 * result + (int) updateType;
        return result;
    }
}
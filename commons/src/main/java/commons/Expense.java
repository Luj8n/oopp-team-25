/*
 * Copyright 2021 Delft University of Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package commons;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Objects;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The class that contains all the info for an expense.
 */
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    String description;
    ArrayList<Person> participants;
    Person receiver;
    BigDecimal paid;
    Tag tag;
    Instant creationDate; // "Detailed Expenses" extension

    /**
     * Creates the Expense class.
     *
     * @param receiver The Person that has paid for the Expense.
     * @param paid     The amount that the Person paid for the Expense.
     */
    public Expense(Person receiver, BigDecimal paid) {
        this.participants = new ArrayList<Person>();
        this.receiver = receiver;
        this.paid = paid;
        this.creationDate = Instant.now();
    }

    /**
     * Creates the Expense class with a date.
     *
     * @param receiver     The Person that has paid for the Expense.
     * @param paid         The amount that the Person paid for the Expense.
     * @param creationDate Creation date of the Expense.
     */
    public Expense(Person receiver, BigDecimal paid, Instant creationDate) {
        this.participants = new ArrayList<Person>();
        this.receiver = receiver;
        this.paid = paid;
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addParticipant(Person participant) {
        participants.add(participant);
    }

    public void removeParticipant(Person participant) {
        participants.remove(participant);
    }

    /**
     * gets the share that should be paid /person.
     *
     * @return the share a person needs to pay for this expense;
     */
    public BigDecimal getShare() {
        int totalNoParticipants = participants.size() + 1;
        // TODO: return (Money) paid/totalNoParticipants
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }


    public ArrayList<Person> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<Person> participants) {
        this.participants = participants;
    }

    public Person getReceiver() {
        return receiver;
    }

    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }

    public BigDecimal getPaid() {
        return paid;
    }

    public void setPaid(BigDecimal paid) {
        this.paid = paid;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }
}

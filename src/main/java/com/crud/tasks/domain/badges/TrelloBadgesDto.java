package com.crud.tasks.domain.badges;

import com.crud.tasks.domain.badges.AttachmentsByTypeDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloBadgesDto {
    @JsonProperty("votes")
    private int votes;
    @JsonProperty("attachmentsByType")
    private AttachmentsByTypeDto attachmentsByTypeDto;

    @Override
    public String toString() {
        return "TrelloBadgesDto{" +
                "votes=" + votes +
                ", attachmentsByTypeDto=" + attachmentsByTypeDto +
                '}';
    }
}

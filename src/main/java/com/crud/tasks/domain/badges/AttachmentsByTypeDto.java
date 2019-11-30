package com.crud.tasks.domain.badges;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttachmentsByTypeDto {
    @JsonProperty("trello")
    private BadgesTrelloDto badgesTrelloDto;

    @Override
    public String toString() {
        return "AttachmentsByTypeDto{" +
                "badgesTrelloDto=" + badgesTrelloDto +
                '}';
    }
}

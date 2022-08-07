package com.isakov.emcore.job.dto.headhunter.vacancies;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
/* _example
@JsonPropertyOrder({
        "requirement",
        "responsibility"
})
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
 */
@Data
public class SnippetDTO {

    //_example @JsonProperty("requirement")
    private String requirement;
    //_example @JsonProperty("responsibility")
    private String responsibility;

}
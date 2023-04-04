package com.cbn.springai.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CompletationResponse {
    String object;
    String text;
    String index;
    String logprobs;
    @JsonProperty("finish_reason")
    String finishReason;
}

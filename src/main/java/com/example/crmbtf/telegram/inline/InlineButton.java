package com.example.crmbtf.telegram.inline;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InlineButton {
    private String text;
    private String url;
}

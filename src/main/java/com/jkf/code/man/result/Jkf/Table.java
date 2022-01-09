package com.jkf.code.man.result.Jkf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: ${author}
 * @time: ${time}
 * @desc: ${desc}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Table implements Serializable {
    private Long id;
    private String name;
}

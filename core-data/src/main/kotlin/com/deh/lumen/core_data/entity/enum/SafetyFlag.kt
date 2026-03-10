package com.deh.lumen.core_data.entity.enum

enum class SafetyFlag {
    CLEAR,        // classified, nothing concerning detected
    LOW,          // mild language, no action taken
    MEDIUM,       // gentle resource card surfaced to user
    HIGH          // prominent resources surfaced to user
}
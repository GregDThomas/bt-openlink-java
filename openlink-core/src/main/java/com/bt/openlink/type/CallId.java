package com.bt.openlink.type;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class CallId extends AbstractType<String> {

    private CallId(final String value) {
        super(value);
    }

    @Nonnull
    public static Optional<CallId> from(@Nullable final String value) {
        return value == null || value.isEmpty() ? Optional.empty() : Optional.of(new CallId(value));
    }

}

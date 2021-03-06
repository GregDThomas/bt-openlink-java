package com.bt.openlink.iq;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class GetProfilesRequestBuilder<B extends GetProfilesRequestBuilder, J, T extends Enum<T>> extends IQBuilder<B, J, T> {

    @Nullable private J jid;

    protected GetProfilesRequestBuilder(final Class<T> typeClass) {
        super(typeClass);
    }

    @Nonnull
    @Override
    public String getExpectedIQType() {
        return "set";
    }

    @Nonnull
    @SuppressWarnings("unchecked")
    public B setJID(@Nonnull final J jid) {
        this.jid = jid;
        return (B) this;
    }

    @Nonnull
    public Optional<J> getJID() {
        return Optional.ofNullable(jid);
    }

    @Override
    protected void validate() {
        super.validate();
        if (jid == null) {
            throw new IllegalStateException("The get-profiles request 'jid' has not been set");
        }
    }

    @Override
    public void validate(final List<String> errors) {
        validate(errors, true);
    }

    protected void validate(List<String> errors, boolean checkIQFields) {
        if (checkIQFields) {
            super.validate(errors);
        }
        if (jid == null) {
            errors.add("Invalid get-profiles request stanza; missing or invalid 'jid'");
        }
    }
}

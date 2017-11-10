package com.bt.openlink.tinder.iq;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import static org.xmlunit.matchers.CompareMatcher.isIdenticalTo;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.bt.openlink.tinder.Fixtures;
import com.bt.openlink.type.Call;
import com.bt.openlink.type.CallDirection;
import com.bt.openlink.type.CallState;
import com.bt.openlink.type.Changed;

@SuppressWarnings("ConstantConditions")
public class MakeCallResultTest {
    private static final String MAKE_CALL_RESULT = "<iq from='" + Fixtures.FROM_JID + "' to='" + Fixtures.TO_JID + "' id='" + Fixtures.STANZA_ID + "' type='result'>\n" +
            "   <command xmlns=\"http://jabber.org/protocol/commands\" node=\"http://xmpp.org/protocol/openlink:01:00:00#make-call\" status=\"completed\">\n" +
            "     <iodata xmlns=\"urn:xmpp:tmp:io-data\" type=\"output\">\n" +
            "      <out>\n" +
            "        <callstatus xmlns='http://xmpp.org/protocol/openlink:01:00:00#call-status' busy='true'>\n" +
            "          <call>\n" +
            "            <id>" + Fixtures.CALL_ID + "</id>\n" +
            "            <site id='42' type='BTSM' default='true'>test-site-name</site>\n" +
            "            <profile>" + Fixtures.PROFILE_ID + "</profile>\n" +
            "            <interest>" + Fixtures.INTEREST_ID + "</interest>\n" +
            "            <changed>State</changed>\n" +
            "            <state>CallOriginated</state>\n" +
            "            <direction>Outgoing</direction>\n" +
            "            <caller>\n" +
            "              <number e164='" + Fixtures.CALLER_E164_NUMBER + "'>" + Fixtures.CALLER_NUMBER + "</number>\n" +
            "              <name>" + Fixtures.CALLER_NAME + "</name>\n" +
            "            </caller>\n" +
            "            <called>\n" +
            "              <number e164='" + Fixtures.CALLED_E164_NUMBER + "' destination='" + Fixtures.CALLED_DESTINATION + "'>" + Fixtures.CALLED_NUMBER + "</number>\n" +
            "              <name>" + Fixtures.CALLED_NAME + "</name>\n" +
            "            </called>\n" +
            "            <starttime>2017-10-09T08:07:00.000Z</starttime>\n" +
            "            <duration>60000</duration>\n" +
            "            <actions>\n" +
            "              <AnswerCall/>\n" +
            "            </actions>\n" +
            "            <features>\n" +
            "              <feature id='hs_1' type='Handset' label='Handset 1'>false</feature>\n" +
            "              <feature id='hs_2' type='Handset' label='Handset 2'>false</feature>\n" +
            "              <feature id='priv_1' type='Privacy' label='Privacy'>false</feature>\n" +
            "              <feature id='NetrixHiTouch_sales1' type='DeviceKeys' label='NetrixHiTouch'>\n" +
            "                <devicekeys xmlns='http://xmpp.org/protocol/openlink:01:00:00/features#device-keys'>\n" +
            "                  <key>key_1:1:1</key>\n" +
            "                </devicekeys>\n" +
            "              </feature>\n" +
            "            </features>\n" +
            "            <participants>\n" +
            "              <participant direction=\"Incoming\" duration=\"60000\" jid=\"test-user@test-domain\" starttime=\"2017-10-09T08:07:00.000Z\" timestamp=\"Mon Oct 09 08:07:00 UTC 2017\" type=\"Active\"/>\n" +
            "            </participants>\n" +
            "          </call>\n" +
            "        </callstatus>\n" +
            "      </out>\n" +
            "    </iodata>\n" +
            "  </command>\n" +
            "</iq>";

    @Rule public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void canBuildAStanza() throws Exception {

        final MakeCallResult result = MakeCallResult.Builder.start()
                .setTo(Fixtures.TO_JID)
                .setFrom(Fixtures.FROM_JID)
                .addCall(Fixtures.CALL)
                .build();

        assertThat(result.getCalls(), contains(Fixtures.CALL));
    }

    @Test
    public void willGenerateAnXmppStanza() throws Exception {

        //    TODO: (Greg 2017-09-27) Replace this with MAKE_CALL_RESULT when fully implemented
        final String expectedXML = "<iq from='" + Fixtures.FROM_JID + "' to='" + Fixtures.TO_JID + "' id='" + Fixtures.STANZA_ID + "' type='result'>\n" +
                "   <command xmlns=\"http://jabber.org/protocol/commands\" node=\"http://xmpp.org/protocol/openlink:01:00:00#make-call\" status=\"completed\">\n" +
                "     <iodata xmlns=\"urn:xmpp:tmp:io-data\" type=\"output\">\n" +
                "      <out>\n" +
                "        <callstatus xmlns='http://xmpp.org/protocol/openlink:01:00:00#call-status'>\n" +
                "          <call>\n" +
                "            <id>" + Fixtures.CALL_ID + "</id>\n" +
                "            <site default='true' id='42' type='BTSM'>test-site-name</site>" +
                "            <profile>" + Fixtures.PROFILE_ID + "</profile>\n" +
                "            <interest>" + Fixtures.INTEREST_ID + "</interest>\n" +
                "            <changed>State</changed>\n" +
                "            <state>CallOriginated</state>\n" +
                "            <direction>Incoming</direction>\n" +
                "            <caller>\n" +
                "              <number e164='" + Fixtures.CALLER_E164_NUMBER + "'>" + Fixtures.CALLER_NUMBER + "</number>\n" +
                "              <name>" + Fixtures.CALLER_NAME + "</name>\n" +
                "            </caller>\n" +
                "            <called>\n" +
                "              <number e164='" + Fixtures.CALLED_E164_NUMBER + "' destination='" + Fixtures.CALLED_DESTINATION + "'>" + Fixtures.CALLED_NUMBER + "</number>\n" +
                "              <name>" + Fixtures.CALLED_NAME + "</name>\n" +
                "            </called>\n" +
                "            <starttime>2017-10-09T08:07:00.000Z</starttime>\n" +
                "            <duration>60000</duration>\n" +
                "            <actions>\n" +
                "              <AnswerCall/>\n" +
                "            </actions>\n" +
                "            <participants>\n" +
                "              <participant direction='Incoming' jid='test-user@test-domain' starttime='2017-10-09T08:07:00.000Z' timestamp='Mon Oct 09 08:07:00 UTC 2017' duration='60000' type='Active'/>\n" +
                "            </participants>\n" +
                "          </call>\n" +
                "        </callstatus>\n" +
                "      </out>\n" +
                "    </iodata>\n" +
                "  </command>\n" +
                "</iq>";

        final MakeCallResult result = MakeCallResult.Builder.start()
                .setId(Fixtures.STANZA_ID)
                .setTo(Fixtures.TO_JID)
                .setFrom(Fixtures.FROM_JID)
                .addCalls(Collections.singletonList(Fixtures.CALL))
                .build();

        assertThat(result.toXML(), isIdenticalTo(MAKE_CALL_RESULT).ignoreWhitespace());
    }

    @Test
    public void willEnsureTheStanzaHasACall() throws Exception {

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("The make-call result has no calls");
        MakeCallResult.Builder.start()
                .setTo(Fixtures.TO_JID)
                .setFrom(Fixtures.FROM_JID)
                .build();
    }

    @Test
    public void willParseAnXmppStanza() throws Exception {

        final MakeCallResult result = (MakeCallResult) OpenlinkIQParser.parse(Fixtures.iqFrom(MAKE_CALL_RESULT));

        assertThat(result.getID(), is(Fixtures.STANZA_ID));
        assertThat(result.getTo(), is(Fixtures.TO_JID));
        assertThat(result.getFrom(), is(Fixtures.FROM_JID));
        final List<Call> calls = result.getCalls();
        final Call theOnlyCall = calls.get(0);
        assertThat(theOnlyCall.getId().get(), is(Fixtures.CALL_ID));
        assertThat(theOnlyCall.getProfileId().get(), is(Fixtures.PROFILE_ID));
        assertThat(theOnlyCall.getInterestId().get(), is(Fixtures.INTEREST_ID));
        assertThat(theOnlyCall.getChanged().get(), is(Changed.STATE));
        assertThat(theOnlyCall.getState().get(), is(CallState.CALL_ORIGINATED));
        assertThat(theOnlyCall.getDirection().get(), is(CallDirection.OUTGOING));
        assertThat(theOnlyCall.getCallerNumber().get(), is(Fixtures.CALLER_NUMBER));
        assertThat(theOnlyCall.getCallerName().get(), is(Fixtures.CALLER_NAME));
        assertThat(theOnlyCall.getCallerE164Numbers(), is(Collections.singletonList(Fixtures.CALLER_E164_NUMBER)));
        assertThat(theOnlyCall.getCalledNumber().get(), is(Fixtures.CALLED_NUMBER));
        assertThat(theOnlyCall.getCalledName().get(), is(Fixtures.CALLED_NAME));
        assertThat(theOnlyCall.getCalledDestination().get(), is(Fixtures.CALLED_DESTINATION));
        assertThat(theOnlyCall.getCalledE164Numbers(), is(Collections.singletonList(Fixtures.CALLED_E164_NUMBER)));
        assertThat(theOnlyCall.getStartTime().get(), is(Fixtures.START_TIME));
        assertThat(theOnlyCall.getDuration().get(), is(Duration.ofMinutes(1)));
        assertThat(calls.size(), is(1));
        assertThat(result.getParseErrors().size(), is(0));
    }

    @Test
    public void willReturnParsingErrors() throws Exception {

        final MakeCallResult result = MakeCallResult.from(Fixtures.iqFrom("<iq>\n" +
                "   <command xmlns=\"http://jabber.org/protocol/commands\" node=\"http://xmpp.org/protocol/openlink:01:00:00#make-call\" status=\"completed\">\n" +
                "     <iodata xmlns=\"urn:xmpp:tmp:io-data\" type=\"output\">\n" +
                "      <out>\n" +
                "      </out>\n" +
                "    </iodata>\n" +
                "  </command>\n" +
                "</iq>"));

        assertThat(result.getParseErrors(), contains(
                "Invalid stanza; missing 'to' attribute is mandatory",
                "Invalid stanza; missing 'from' attribute is mandatory",
                "Invalid stanza; missing 'id' attribute is mandatory",
                "Invalid stanza; missing or incorrect 'type' attribute",
                "Invalid make-call result stanza; missing or invalid calls"));
    }
}
package hudson.scm;

import java.util.ArrayList;
import java.util.Arrays;

import org.jvnet.hudson.test.HudsonTestCase;

public class CvsChangeLogHelperTest extends HudsonTestCase {

    public void testMapCvsLog() {
        String logContents = "cvs rlog: Logging doc\n"
                        + "\n"
                        + "RCS file: /Users/Shared/cvs/doc/rand,v\n"
                        + "head: 1.1\n"
                        + "branch:\n"
                        + "locks: strict\n"
                        + "access list:\n"
                        + "symbolic names:\n"
                        + "keyword substitution: kv\n"
                        + "total revisions: 1; selected revisions: 1\n"
                        + "description:\n"
                        + "----------------------------\n"
                        + "revision 1.1\n"
                        + "date: 2011-12-28 20:22:31 +0000;  author: Michael;  state: Exp;  commitid: 0nRgbNtAi8rCNZMv;\n"
                        + "adding in a test file\n"
                        + "=============================================================================\n";

        CvsModuleLocation location = new CvsModuleLocation(
                        CvsModuleLocationType.HEAD.toString(), null, false,
                        null, false);
        CvsModule module = new CvsModule("doc", null, location);
        CvsRepository repository = new CvsRepository(
                        ":local:/Users/Shared/cvs",
                        Arrays.asList(new CvsModule[] { module }),
                        new ArrayList<ExcludedRegion>(), -1);
        assertEquals("adding in a test file", CvsChangeLogHelper.getInstance()
                        .mapCvsLog(logContents, repository, module)
                        .getChanges().get(0).getMsg());
    }

}

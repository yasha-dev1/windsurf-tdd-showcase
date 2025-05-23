package dst.ass1.jpa.tests;

import dst.ass1.grading.GitHubClassroomGrading;
import dst.ass1.grading.LocalGradingClassRule;
import dst.ass1.grading.LocalGradingRule;
import dst.ass1.jpa.ORMService;
import dst.ass1.jpa.model.IMoney;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Type;
import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

/**
 * Tests if IMoney is implemented correctly.
 */
public class Ass1_1_1_05Test {
    @ClassRule
    public static LocalGradingClassRule afterAll = new LocalGradingClassRule();

    @Rule
    public LocalGradingRule grading = new LocalGradingRule();

    @Rule
    public ORMService orm = new ORMService();

    @GitHubClassroomGrading(maxScore = 1)
    @Test(timeout = 2000)
    public void moneyEntityCannotBePersisted() {
        IMoney money = orm.getModelFactory().createMoney();
        assertNotNull(money);

        money.setCurrencyValue(BigDecimal.TEN);
        money.setCurrency("EURO");

        assertThrows(IllegalArgumentException.class, () -> {
            orm.getEntityManager().persist(money);
        });
    }

    @GitHubClassroomGrading(maxScore = 1)
    @Test(timeout = 2000)
    public void moneyIsEmbeddableType() {
        IMoney money = orm.getModelFactory().createMoney();
        assertNotNull(money);

        ManagedType<?> type = orm.getEntityManager().getMetamodel().managedType(money.getClass());
        assertNotNull(type);
        assertThat(type.getPersistenceType(), is(Type.PersistenceType.EMBEDDABLE));
    }

    @GitHubClassroomGrading(maxScore = 1)
    @Test(timeout = 2000)
    public void moneyHasNoTable() throws Exception {
        assertFalse(orm.getDatabaseGateway().isTable("MONEY"));
    }

}

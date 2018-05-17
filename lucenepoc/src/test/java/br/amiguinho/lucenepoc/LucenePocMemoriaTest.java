package br.amiguinho.lucenepoc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

import java.io.IOException;
import java.util.Set;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.BeforeClass;
import org.junit.Test;

public class LucenePocMemoriaTest {

	private static final String TERESA = "teresa";
	private static final String TIAGO = "tiago";
	private static final String WANDERLEY = "wanderley";
	private static final String ESCUADRA = "ESCUADRA DE COMBINACION, 6\"\" 6U\"";
	
	private static LucenePocMemoria poc;
	
    // Run once, e.g. Database connection, connection pool
    @BeforeClass
    public static void runOnceBeforeClass() {
    	try {
			poc = new LucenePocMemoria();
			poc.addAtIndex(new Item("14369", ESCUADRA));
			poc.addAtIndex(new Item("1", TERESA));
			poc.addAtIndex(new Item("2", TIAGO));
			poc.addAtIndex(new Item("3", WANDERLEY));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @SuppressWarnings("deprecation")
	@Test
    public void testIscuadra() {
        Set<Item> itens = poc.pesquisarDescricao("Iscuadra");
        System.out.println(itens);
        assertThat(itens, not(IsEmptyCollection.empty()));
        assertThat(itens, containsInAnyOrder(hasProperty(LucenePocMemoria.DESCRICAO, is(ESCUADRA))));
    }


    @SuppressWarnings("deprecation")
	@Test
    public void testIscuadro() {
        Set<Item> itens = poc.pesquisarDescricao("Iscuadro");
        System.out.println(itens);
        assertThat(itens, not(IsEmptyCollection.empty()));
        assertThat(itens, containsInAnyOrder(hasProperty(LucenePocMemoria.DESCRICAO, is(ESCUADRA))));
    }
    
    
//    @SuppressWarnings("deprecation")
//	@Test
//    public void testThiago() {
//        Set<Item> itens = poc.pesquisarDescricao("Thiago");
//        System.out.println(itens);
//        assertThat(itens, not(IsEmptyCollection.empty()));
//        assertThat(itens, containsInAnyOrder(hasProperty(LucenePocMemoria.DESCRICAO, is(TIAGO))));
//    }
//
//    @SuppressWarnings("deprecation")
//	@Test
//    public void testTeago() {
//        Set<Item> itens = poc.pesquisarDescricao("Teago");
//        System.out.println(itens);
//        assertThat(itens, not(IsEmptyCollection.empty()));
//        assertThat(itens, containsInAnyOrder(hasProperty(LucenePocMemoria.DESCRICAO, is(TIAGO))));
//    }

    @SuppressWarnings("deprecation")
	@Test
    public void testTheresa() {
        Set<Item> itens = poc.pesquisarDescricao("Theresa");
        System.out.println(itens);
        assertThat(itens, not(IsEmptyCollection.empty()));
        assertThat(itens, containsInAnyOrder(hasProperty(LucenePocMemoria.DESCRICAO, is(TERESA))));
    }

    @SuppressWarnings("deprecation")
	@Test
    public void testThereza() {
        Set<Item> itens = poc.pesquisarDescricao("Thereza");
        System.out.println(itens);
        assertThat(itens, not(IsEmptyCollection.empty()));
        assertThat(itens, containsInAnyOrder(hasProperty(LucenePocMemoria.DESCRICAO, is(TERESA))));
    }

    @SuppressWarnings("deprecation")
	@Test
    public void testVanderlei() {
        Set<Item> itens = poc.pesquisarDescricao("Vanderlei");
        System.out.println(itens);
        assertThat(itens, not(IsEmptyCollection.empty()));
        assertThat(itens, containsInAnyOrder(hasProperty(LucenePocMemoria.DESCRICAO, is(WANDERLEY))));
    }
	
}
